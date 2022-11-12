package com.sorhive.comprojectserver.harvest.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCommentCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHarvestCommentDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHoneyCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHoneyDeleteDto;
import com.sorhive.comprojectserver.harvest.command.application.exception.AlreadyHoneyException;
import com.sorhive.comprojectserver.harvest.command.application.exception.NoHarvestException;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestComment;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestCommentWriter;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestCommentWriterService;
import com.sorhive.comprojectserver.harvest.command.domain.model.honey.Honey;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestCommentRepository;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestRepository;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HoneyRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <pre>
 * Class : HarvestService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           하베스트 댓글 추가 기능 생성
 * 2022-11-12       부시연           허니 추가 기능 생성
 * 2022-11-12       부시연           허니 삭제 기능 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class HarvestService {
    private static final Logger log = LoggerFactory.getLogger(HarvestService.class);
    private final TokenProvider tokenProvider;
    private final HarvestRepository harvestRepository;
    private final HarvestCommentRepository harvestCommentRepository;
    private final HoneyRepository honeyRepository;
    private final HarvestCommentWriterService harvestCommentWriterService;

    public HarvestService(TokenProvider tokenProvider, HarvestRepository harvestRepository, HarvestCommentRepository harvestCommentRepository, HoneyRepository honeyRepository, HarvestCommentWriterService harvestCommentWriterService) {
        this.tokenProvider = tokenProvider;
        this.harvestRepository = harvestRepository;
        this.harvestCommentRepository = harvestCommentRepository;
        this.honeyRepository = honeyRepository;
        this.harvestCommentWriterService = harvestCommentWriterService;
    }

    @Transactional
    public ResponseHarvestCommentDto createHarvestComment(String accessToken, Long harvestId, HarvestCommentCreateDto harvestCommentCreateDto) {

        log.info("[HarvestService] createHarvestComment Start =========================================================");
        log.info("[HarvestService] harvestCommentCreateDto : " + harvestCommentCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        HarvestCommentWriter harvestCommentWriter = harvestCommentWriterService.createHarvestCommentWriter(new MemberCode(memberCode));

        String harvestCommentContent = harvestCommentCreateDto.getHarvestCommentContent();

        Harvest harvest = harvestRepository.findByHarvestId(harvestId);

        if(harvest == null) {
            throw new NoHarvestException();
        }

        HarvestComment harvestComment = new HarvestComment(
                harvestCommentWriter,
                harvestCommentContent,
                harvest
        );

        harvestCommentRepository.save(harvestComment);

        ResponseHarvestCommentDto responseHarvestCommentDto = new ResponseHarvestCommentDto();
        responseHarvestCommentDto.setHarvestCommentId(harvestComment.getId());
        responseHarvestCommentDto.setHarvestCommentcontent(harvestComment.getContent());
        responseHarvestCommentDto.setHarvestCommentCreateTime(harvestComment.getCreateTime());

        return responseHarvestCommentDto;

    }
    @Transactional
    public ResponseHoneyCreateDto createHoney(String accessToken, Long harvestId) {

        log.info("[HarvestService] createHoney Start =========================================================");
        log.info("[HarvestService] harvestId : " + harvestId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Optional<Harvest> harvestData = harvestRepository.findByHarvestIdAndDeleteYnEquals(harvestId, 'N');

        if(harvestData == null) {
            throw new NoHarvestException("해당 하베스트가 존재하지 않습니다.");
        }

        if(!honeyRepository.findByMemberCodeAndHarvestIdAndDeleteYnEquals(new MemberCode(memberCode), new HarvestId(harvestId), 'N').isEmpty() ) {
            throw new AlreadyHoneyException("이미 허니를 했습니다.");
        }

        Harvest harvest = harvestData.get();
        harvest.setHoneyCount(1L);
        harvestRepository.save(harvest);

        Honey honey = new Honey(
                new MemberCode(memberCode),
                new HarvestId(harvestId)
        );

        honeyRepository.save(honey);

        ResponseHoneyCreateDto responseHoneyCreateDto = new ResponseHoneyCreateDto();
        responseHoneyCreateDto.setHoneyId(honey.getId());
        responseHoneyCreateDto.setHarvestId(honey.getHarvestId().getValue());
        responseHoneyCreateDto.setMemberCode(honey.getMemberCode().getValue());
        responseHoneyCreateDto.setCreateTime(honey.getCreateTime());

        return responseHoneyCreateDto;

    }

    @Transactional
    public ResponseHoneyDeleteDto deleteHoney(String accessToken, Long harvestId) {

        log.info("[HarvestService] deleteHoney Start =========================================================");
        log.info("[HarvestService] harvestId : " + harvestId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Optional<Harvest> harvestData = harvestRepository.findByHarvestIdAndDeleteYnEquals(harvestId, 'N');

        if(harvestData == null) {
            throw new NoHarvestException();
        }

        Harvest harvest = harvestData.get();
        harvest.setHoneyCount(-1L);
        harvestRepository.save(harvest);

        Optional<Honey> honeyData = honeyRepository.findByMemberCodeAndHarvestIdAndDeleteYnEquals(new MemberCode(memberCode), new HarvestId(harvestId), 'N');

        Honey honey = honeyData.get();
        honey.setDeleteYn('Y');
        honeyRepository.save(honey);

        ResponseHoneyDeleteDto responseHoneyDeleteDto = new ResponseHoneyDeleteDto();
        responseHoneyDeleteDto.setHoneyId(honey.getId());
        responseHoneyDeleteDto.setHarvestId(honey.getHarvestId().getValue());
        responseHoneyDeleteDto.setMemberCode(honey.getMemberCode().getValue());
        responseHoneyDeleteDto.setDeleteTime(honey.getDeleteTime());

        return responseHoneyDeleteDto;

    }
}
