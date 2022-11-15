package com.sorhive.comprojectserver.lifing.command.application.service;

import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.application.dto.*;
import com.sorhive.comprojectserver.lifing.command.application.exception.AlreadyHoneyException;
import com.sorhive.comprojectserver.lifing.command.application.exception.NoLifingException;
import com.sorhive.comprojectserver.lifing.command.domain.model.honey.Honey;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingId;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingComment;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.repository.HoneyRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingCommentRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.command.infra.NoLifingNoException;
import com.sorhive.comprojectserver.lifing.query.LifingMapper;
import com.sorhive.comprojectserver.lifing.query.dto.LifingImagePath;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : LifingService
 * Comment: 라이핑 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           라이핑 저장
 * 2022-11-15       부시연           허니 추가
 * 2022-11-15       부시연           허니 제거
 * 2022-11-15       부시연           라이핑 댓글 저장
 * 2022-11-16       부시연           라이핑 이미지 리스트로 변경
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class LifingService {

    private static final Logger log = LoggerFactory.getLogger(LifingService.class);
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final LifingMapper lifingMapper;
    private final MemberRepository memberRepository;
    private final HoneyRepository honeyRepository;
    private final LifingCommentWriterService lifingCommentWriterService;
    private final LifingCommentRepository lifingCommentRepository;

    public LifingService(LifingRepository lifingRepository, LifingWriterService lifingWriterService, TokenProvider tokenProvider, LifingMapper lifingMapper, MemberRepository memberRepository, HoneyRepository honeyRepository, LifingCommentWriterService lifingCommentWriterService, LifingCommentRepository lifingCommentRepository) {

        this.lifingRepository = lifingRepository;
        this.lifingWriterService = lifingWriterService;
        this.tokenProvider = tokenProvider;
        this.lifingMapper = lifingMapper;
        this.memberRepository = memberRepository;
        this.honeyRepository = honeyRepository;

        this.lifingCommentWriterService = lifingCommentWriterService;
        this.lifingCommentRepository = lifingCommentRepository;
    }


    /** 라이핑 생성하기 */
    public ResponseLifingDto createLifing(String accessToken, LifingCreateDto lifingCreateDto) {

        log.info("[LifingService] createLifing Start =====================");
        log.info("[LifingService] lifingCreateDto " + lifingCreateDto);

        /* 토큰에서 멤버 코드 값 꺼내오기 */
        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 라이핑 내용 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingContent() == null) {
            throw new NoContentException("라이핑에 내용이 없습니다.");
        }

        /* 라이핑 번호가 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingNo() == null) {
            throw new NoLifingNoException("라이핑 번호가 없습니다.");
        }

        /* 라이핑 카테고리 번호가 없을 경우 예외처리 */
        if(lifingCreateDto.getLifingCategoryNo() == null) {
            throw new NoLifingNoException("라이핑 카테고리 번호가 없습니다.");
        }

        /* 라이핑 작성자 만들기 */
        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        Long lifingNo = lifingCreateDto.getLifingNo();
        Long lifingCategoryNo = lifingCreateDto.getLifingCategoryNo();
        String lifingConetent = lifingCreateDto.getLifingContent();

        /* 라이핑 생성하기 */
        Lifing lifing = new Lifing(
                lifingWriter,
                lifingNo,
                lifingCategoryNo,
                lifingConetent
        );

        /* 라이핑 저장하기 */
        lifingRepository.save(lifing);

        /* 라이핑 번호를 저장하기 위해 멤버 데이터 조회하기 */
        Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
        Member member = memberData.get();
        member.changeLifingWithAI(lifingNo, lifingCategoryNo);

        /* 멤버에 라이핑번호 정보 저장하기 */
        memberRepository.save(member);

        /* 라이핑 생성 반환 전송 객체 만들기 */
        ResponseLifingDto responseLifingDto = new ResponseLifingDto();

        responseLifingDto.setLifingId(lifing.getLifingId());
        responseLifingDto.setLifingContent(lifing.getLifingConetent());
        responseLifingDto.setLifingNo(lifing.getLifingNo());
        responseLifingDto.setLifingCategoryNo(lifing.getLifingCategoryNo());
        responseLifingDto.setLifingCreateTime(lifing.getCreateTime());
        responseLifingDto.setLifingWriter(lifing.getLifingWriter());

        return responseLifingDto;
    }


    /** 허니 추가 */
    @Transactional
    public ResponseHoneyCreateDto createHoney(String accessToken, Long lifingId) {

        log.info("[LifingService] createHoney Start =========================================================");
        log.info("[LifingService] lifingId : " + lifingId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'N');

        if(lifingData == null) {
            throw new NoLifingException("해당 라이핑이 존재하지 않습니다.");
        }

        if(!honeyRepository.findByMemberCodeAndLifingIdAndDeleteYnEquals(new MemberCode(memberCode), new LifingId(lifingId), 'N').isEmpty() ) {
            throw new AlreadyHoneyException("이미 허니를 했습니다.");
        }

        Lifing lifing = lifingData.get();
        lifing.setHoneyCount(1);
        lifingRepository.save(lifing);

        Honey honey = new Honey(
                new MemberCode(memberCode),
                new LifingId(lifingId)
        );

        honeyRepository.save(honey);

        ResponseHoneyCreateDto responseHoneyCreateDto = new ResponseHoneyCreateDto();

        responseHoneyCreateDto.setHoneyId(honey.getId());
        responseHoneyCreateDto.setLifingId(honey.getLifingId().getValue());
        responseHoneyCreateDto.setMemberCode(honey.getMemberCode().getValue());
        responseHoneyCreateDto.setCreateTime(honey.getCreateTime());

        return responseHoneyCreateDto;

    }

    /** 허니 취소 */
    @Transactional
    public ResponseHoneyDeleteDto deleteHoney(String accessToken, Long lifingId) {

        log.info("[LifingService] deleteHoney Start =========================================================");
        log.info("[LifingService] lifingId : " + lifingId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Optional<Lifing> lifingData = lifingRepository.findByLifingIdAndDeleteYnEquals(lifingId, 'N');

        if(lifingData == null) {
            throw new NoLifingException("해당 라이핑이 존재하지 않습니다.");
        }

        Lifing lifing = lifingData.get();
        lifing.setHoneyCount(-1);
        lifingRepository.save(lifing);

        Optional<Honey> honeyData = honeyRepository.findByMemberCodeAndLifingIdAndDeleteYnEquals(new MemberCode(memberCode), new LifingId(lifingId), 'N');

        Honey honey = honeyData.get();
        honey.setDeleteYn('Y');
        honeyRepository.save(honey);

        ResponseHoneyDeleteDto responseHoneyDeleteDto = new ResponseHoneyDeleteDto();

        responseHoneyDeleteDto.setHoneyId(honey.getId());
        responseHoneyDeleteDto.setLifingId(honey.getLifingId().getValue());
        responseHoneyDeleteDto.setMemberCode(honey.getMemberCode().getValue());
        responseHoneyDeleteDto.setDeleteTime(honey.getDeleteTime());

        return responseHoneyDeleteDto;

    }

    /** 라이핑 댓글 작성 */
    @Transactional
    public ResponseLifingCommentDto createLifingComment(String accessToken, Long lifingId, LifingCommentCreateDto lifingCommentCreateDto) {

        log.info("[LifingService] createLifingComment Start =========================================================");
        log.info("[LifingService] lifingCommentCreateDto : " + lifingCommentCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        LifingCommentWriter lifingCommentWriter = lifingCommentWriterService.createLifingCommentWriter(new MemberCode(memberCode));

        String lifingCommentContent = lifingCommentCreateDto.getLifingCommentContent();

        Lifing lifing = lifingRepository.findByLifingId(lifingId);

        if(lifing == null) {
            throw new NoLifingException("해당 라이핑이 존재하지 않습니다.");
        }

        LifingComment lifingComment = new LifingComment(
                lifingCommentWriter,
                lifingCommentContent,
                lifing
        );

        lifingCommentRepository.save(lifingComment);

        ResponseLifingCommentDto responseLifingCommentDto = new ResponseLifingCommentDto();

        responseLifingCommentDto.setLifingCommentId(lifingComment.getLifingCommentId());
        responseLifingCommentDto.setLifingCommentcontent(lifingComment.getLifingCommentContent());
        responseLifingCommentDto.setLifingCommentCreateTime(lifingComment.getLifingCommentCreateTime());

        return responseLifingCommentDto;

    }

}
