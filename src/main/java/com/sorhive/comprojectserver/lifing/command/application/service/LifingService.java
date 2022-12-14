package com.sorhive.comprojectserver.lifing.command.application.service;

import com.sorhive.comprojectserver.common.exception.AlreadyDeleteException;
import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.common.exception.NotSameWriterException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.application.dto.*;
import com.sorhive.comprojectserver.lifing.command.application.exception.AlreadyHoneyException;
import com.sorhive.comprojectserver.lifing.command.application.exception.NoLifingException;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingId;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingComment;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifinghoney.LifingHoney;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingCommentRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingHoneyRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.command.infra.NoLifingNoException;
import com.sorhive.comprojectserver.lifing.exception.NoLifingCommentException;
import com.sorhive.comprojectserver.lifing.query.LifingMapper;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <pre>
 * Class : LifingService
 * Comment: ????????? ?????????
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       ?????????           ?????? ??????
 * 2022-11-12       ?????????           ????????? ??????
 * 2022-11-15       ?????????           ?????? ??????
 * 2022-11-15       ?????????           ?????? ??????
 * 2022-11-15       ?????????           ????????? ?????? ??????
 * 2022-11-16       ?????????           ????????? ????????? ???????????? ??????
 * 2022-11-16       ?????????           ????????? ?????? ??????
 * 2022-11-17       ?????????           ????????? ?????? ??????
 * </pre>
 *
 * @author ?????????(?????? ?????????)
 * @version 1(????????? ??????)
 * @see (????????? class ?????? ?????? url)
 */
@Service
public class LifingService {

    private static final Logger log = LoggerFactory.getLogger(LifingService.class);
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final LifingHoneyRepository lifingHoneyRepository;
    private final LifingCommentWriterService lifingCommentWriterService;
    private final LifingCommentRepository lifingCommentRepository;

    public LifingService(LifingRepository lifingRepository, LifingWriterService lifingWriterService, TokenProvider tokenProvider, MemberRepository memberRepository, LifingHoneyRepository lifingHoneyRepository, LifingCommentWriterService lifingCommentWriterService, LifingCommentRepository lifingCommentRepository) {

        this.lifingRepository = lifingRepository;
        this.lifingWriterService = lifingWriterService;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.lifingHoneyRepository = lifingHoneyRepository;

        this.lifingCommentWriterService = lifingCommentWriterService;
        this.lifingCommentRepository = lifingCommentRepository;
    }


    /** ????????? ???????????? */
    public LifingResponseDto createLifing(String accessToken, LifingCreateDto lifingCreateDto) {

        log.info("[LifingService] createLifing Start =====================");
        log.info("[LifingService] lifingCreateDto " + lifingCreateDto);

        /* ???????????? ?????? ?????? ??? ???????????? */
        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* ????????? ?????? ?????? ?????? ???????????? */
        if(lifingCreateDto.getLifingContent() == null) {
            throw new NoContentException("???????????? ????????? ????????????.");
        }

        /* ????????? ????????? ?????? ?????? ???????????? */
        if(lifingCreateDto.getLifingNo() == null) {
            throw new NoLifingNoException("????????? ????????? ????????????.");
        }
        
        /* ????????? ???????????? ?????? ?????? ???????????? */
        if(lifingCreateDto.getLifingId() == null) {
            throw new NoLifingNoException("????????? ???????????? ????????????.");
        }

        /* ????????? ???????????? ????????? ?????? ?????? ???????????? */
        if(lifingCreateDto.getLifingCategoryNo() == null) {
            throw new NoLifingNoException("????????? ???????????? ????????? ????????????.");
        }

        /* ????????? ????????? ????????? */
        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        Long lifingNo = lifingCreateDto.getLifingNo();
        Long lifingCategoryNo = lifingCreateDto.getLifingCategoryNo();
        String lifingConetent = lifingCreateDto.getLifingContent();

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingCreateDto.getLifingId(), 'N');

        Lifing lifing = lifingData.get();

        /* ????????? ???????????? */
        lifing.createNewAiLifing(
                lifingWriter,
                lifingNo,
                lifingCategoryNo,
                lifingConetent
        );

        /* ????????? ???????????? */
        lifingRepository.save(lifing);

        /* ????????? ????????? ???????????? ?????? ?????? ????????? ???????????? */
        Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
        Member member = memberData.get();
        member.changeLifingWithAI(lifingNo, lifingCategoryNo);

        /* ????????? ??????????????? ?????? ???????????? */
        memberRepository.save(member);

        /* ????????? ?????? ?????? ?????? ?????? ????????? */
        LifingResponseDto lifingResponseDto = new LifingResponseDto(
                lifing.getLifingId(),
                lifing.getLifingConetent(),
                lifing.getLifingNo(),
                lifing.getLifingCategoryNo(),
                lifing.getCreateTime(),
                lifing.getLifingWriter()
        );

        return lifingResponseDto;
    }


    /** ?????? ?????? */
    @Transactional
    public LifingHoneyCreateResponseDto createLifingHoney(String accessToken, Long lifingId) {

        log.info("[LifingService] createLifingHoney Start =========================================================");
        log.info("[LifingService] lifingId : " + lifingId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(lifingRepository.findByLifingId(lifingId) == null) {
            throw new NoLifingException("?????? ???????????? ???????????? ????????????.");
        }

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'N');

        if(!lifingHoneyRepository.findByMemberCodeAndLifingIdAndDeleteYnEquals(new MemberCode(memberCode), new LifingId(lifingId), 'N').isEmpty() ) {
            throw new AlreadyHoneyException("?????? ????????? ????????????.");
        }

        Lifing lifing = lifingData.get();
        lifing.countingHoney(1);
        lifingRepository.save(lifing);

        LifingHoney lifingHoney = new LifingHoney(
                new MemberCode(memberCode),
                new LifingId(lifingId)
        );

        lifingHoneyRepository.save(lifingHoney);

        LifingHoneyCreateResponseDto lifingHoneyCreateResponseDto = new LifingHoneyCreateResponseDto(
                lifingHoney.getId(),
                lifingHoney.getMemberCode().getValue(),
                lifingHoney.getLifingId().getValue(),
                lifingHoney.getCreateTime()
        );

        return lifingHoneyCreateResponseDto;

    }

    /** ?????? ?????? */
    @Transactional
    public LifingHoneyDeleteResponseDto deleteLifingHoney(String accessToken, Long lifingId) {

        log.info("[LifingService] deleteLifingHoney Start =========================================================");
        log.info("[LifingService] lifingId : " + lifingId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(lifingRepository.findByLifingId(lifingId) == null) {
            throw new NoLifingException("?????? ???????????? ???????????? ????????????.");
        }

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'N');

        Lifing lifing = lifingData.get();
        lifing.countingHoney(-1);
        lifingRepository.save(lifing);

        if(lifingHoneyRepository.findByMemberCodeAndLifingIdAndDeleteYnEquals(new MemberCode(memberCode), new LifingId(lifingId), 'N').isEmpty()) {
            throw new AlreadyDeleteException("?????? ????????? ?????? ???????????????.");
        }

        Optional<LifingHoney> honeyData = lifingHoneyRepository.findByMemberCodeAndLifingIdAndDeleteYnEquals(new MemberCode(memberCode), new LifingId(lifingId), 'N');

        LifingHoney lifingHoney = honeyData.get();
        lifingHoney.setDeleteYn('Y');
        lifingHoneyRepository.save(lifingHoney);

        LifingHoneyDeleteResponseDto lifingHoneyDeleteResponseDto = new LifingHoneyDeleteResponseDto(
                lifingHoney.getId(),
                lifingHoney.getLifingId().getValue(),
                lifingHoney.getMemberCode().getValue(),
                lifingHoney.getDeleteTime()
        );

        return lifingHoneyDeleteResponseDto;

    }

    /** ????????? ?????? ?????? */
    @Transactional
    public LifingCommentResponseDto createLifingComment(String accessToken, Long lifingId, LifingCommentCreateDto lifingCommentCreateDto) {

        log.info("[LifingService] createLifingComment Start =========================================================");
        log.info("[LifingService] lifingCommentCreateDto : " + lifingCommentCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        LifingCommentWriter lifingCommentWriter = lifingCommentWriterService.createLifingCommentWriter(new MemberCode(memberCode));

        String lifingCommentContent = lifingCommentCreateDto.getLifingCommentContent();

        Lifing lifing = lifingRepository.findByLifingId(lifingId);

        if(lifing == null) {
            throw new NoLifingException("?????? ???????????? ???????????? ????????????.");
        }

        LifingComment lifingComment = new LifingComment(
                lifingCommentWriter,
                lifingCommentContent,
                lifing
        );

        lifingCommentRepository.save(lifingComment);

        LifingCommentResponseDto lifingCommentResponseDto = new LifingCommentResponseDto(
                lifingComment.getLifingCommentId(),
                lifingComment.getLifingCommentContent(),
                lifingComment.getLifingCommentCreateTime()
        );

        return lifingCommentResponseDto;

    }

    /** ????????? ?????? ?????? */
    public Long deleteLifingComment(String accessToken, Long lifingCommentId) {

        log.info("[LifingService] deleteLifingComment Start =========================================================");
        log.info("[LifingService] lifingCommentId : " + lifingCommentId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(lifingCommentRepository.findByLifingCommentId(lifingCommentId) == null) {
            throw new NoLifingCommentException("?????? ????????? ?????? ?????? ????????????");
        }

        if(lifingCommentRepository.findByLifingCommentIdAndLifingCommentDeleteYnEquals(lifingCommentId, 'Y') != null) {
            throw new NoLifingCommentException("?????? ????????? ?????? ????????? ???????????????.");
        }

        Optional<LifingComment> lifingCommentData = Optional.ofNullable(lifingCommentRepository.findByLifingCommentIdAndLifingCommentDeleteYnEquals(lifingCommentId, 'N'));
        LifingComment lifingComment = lifingCommentData.get() ;

        if(lifingComment.getLifingCommentWriter().getLifingCommentWriterCode().getValue() != memberCode) {
            throw new NotSameWriterException("?????? ?????? ???????????? ???????????? ????????????.");
        }

        lifingComment.deleteComment('Y');
        lifingCommentRepository.save(lifingComment);

        return lifingComment.getLifingCommentId();

    }

    /** ????????? ?????? ?????? */
    public LifingCommentUpdateResponseDto updateLifingComment(String accessToken, LifingCommentUpdateRequestDto lifingCommentUpdateRequestDto) {

        log.info("[LifingService] updateLifingComment Start =========================================================");
        log.info("[LifingService] lifingCommentUpdateDto : " + lifingCommentUpdateRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if((lifingCommentUpdateRequestDto.getLifingCommentId() == null) || (lifingCommentUpdateRequestDto.getLifingCommentContent() == null)) {
            throw new NoContentException("?????? ?????? ????????? ????????? ????????? ????????????.");
        }

        String lifingCommentContent = lifingCommentUpdateRequestDto.getLifingCommentContent();
        Long lifingCommentId = lifingCommentUpdateRequestDto.getLifingCommentId();

        if(lifingCommentRepository.findByLifingCommentId(lifingCommentId) == null) {
            throw new NoLifingCommentException("?????? ????????? ?????? ?????? ????????????");
        }

        if(lifingCommentRepository.findByLifingCommentIdAndLifingCommentDeleteYnEquals(lifingCommentId, 'Y') != null) {
            throw new NoLifingCommentException("?????? ????????? ?????? ????????? ???????????????.");
        }

        Optional<LifingComment> lifingCommentData = Optional.ofNullable(lifingCommentRepository.findByLifingCommentIdAndLifingCommentDeleteYnEquals(lifingCommentId, 'N'));
        LifingComment lifingComment = lifingCommentData.get() ;

        if(lifingComment.getLifingCommentWriter().getLifingCommentWriterCode().getValue() != memberCode) {
            throw new NotSameWriterException("?????? ?????? ???????????? ???????????? ????????????.");
        }

        lifingComment.updateComment(lifingCommentContent);
        lifingCommentRepository.save(lifingComment);

        /* ????????? ?????? ?????? ?????? ??????????????? ?????? ????????? ????????????. */
        LifingCommentUpdateResponseDto lifingCommentUpdateResponseDto = new LifingCommentUpdateResponseDto(
                lifingComment.getLifingCommentId(),
                lifingComment.getLifingCommentContent(),
                lifingComment.getLifingCommentWriter(),
                lifingComment.getLifingCommentUploadTime()
        );

        return lifingCommentUpdateResponseDto;

    }
}
