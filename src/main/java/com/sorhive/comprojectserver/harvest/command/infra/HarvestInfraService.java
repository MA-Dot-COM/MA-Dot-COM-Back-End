package com.sorhive.comprojectserver.harvest.command.infra;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHarvestDto;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriter;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriterService;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class HarvestInfraService {

    private final TokenProvider tokenProvider;
    private final HarvestRepository harvestRepository;
    private final HarvestWriterService harvestWriterService;
    public Object createLifing(String accessToken, HarvestCreateDto harvestCreateDto) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        HarvestWriter harvestWriter = harvestWriterService.createHarvestWriter(new MemberCode(memberCode));

        String harvestContent = harvestCreateDto.getHarvestContent();

        Harvest harvest = new Harvest(
                harvestWriter,
                harvestContent
        );

        harvestRepository.save(harvest);

        ResponseHarvestDto responseHarvestDto = new ResponseHarvestDto();
        responseHarvestDto.setHarvestId(harvest.getHarvestId());
        responseHarvestDto.setHarvestCreateTime(harvest.getCreateTime());
        responseHarvestDto.setHarvestContent(harvest.getHarvestContent());

        if(harvestCreateDto.getHarvestImage() != null) {
            for (byte[] harvestImage : harvestCreateDto.getHarvestImage()) {


//                responseHarvestDto.setHarvestImage();
            }
        }


        return responseHarvestDto;
    }
}
