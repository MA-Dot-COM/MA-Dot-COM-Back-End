package com.sorhive.comprojectserver.harvest.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCommentCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHarvestCommentDto;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestComment;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestCommentWriter;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestCommentWriterService;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.NoHarvestException;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestCommentRepository;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : HarvestService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
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
    private final HarvestCommentWriterService harvestCommentWriterService;

    public HarvestService(TokenProvider tokenProvider, HarvestRepository harvestRepository, HarvestCommentRepository harvestCommentRepository, HarvestCommentWriterService harvestCommentWriterService) {
        this.tokenProvider = tokenProvider;
        this.harvestRepository = harvestRepository;
        this.harvestCommentRepository = harvestCommentRepository;
        this.harvestCommentWriterService = harvestCommentWriterService;
    }


    public Object createHarvestComment(String accessToken, Long harvestId, HarvestCommentCreateDto harvestCommentCreateDto) {

        log.info("[HarvestInfraService] Start =========================================================");
        log.info("[HarvestInfraService] harvestCreateDto : " + harvestCommentCreateDto);

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
}
