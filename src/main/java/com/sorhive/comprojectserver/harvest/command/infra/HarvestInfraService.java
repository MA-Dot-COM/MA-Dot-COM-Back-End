package com.sorhive.comprojectserver.harvest.command.infra;

import com.sorhive.comprojectserver.config.file.S3HarvestImageFile;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHarvestDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.ResponseHarvestImageDto;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriter;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestWriterService;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestimage.HarvestImage;
import com.sorhive.comprojectserver.harvest.command.domain.repository.HarvestRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@RequiredArgsConstructor
public class HarvestInfraService {

    private static final Logger log = LoggerFactory.getLogger(HarvestInfraService.class);
    private final TokenProvider tokenProvider;
    private final HarvestRepository harvestRepository;
    private final HarvestWriterService harvestWriterService;
    private final S3HarvestImageFile s3HarvestImageFile;

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

        try {
            if(harvestCreateDto.getHarvestImage() != null) {

                List<String> harvestImagePathList = new ArrayList<>();

                for (int i = 0; i < harvestCreateDto.getHarvestImage().size(); i++) {

                    byte[] harvestByteImage = harvestCreateDto.getHarvestImage().get(i).getHarvestImage();

                    String originalName = harvestCreateDto.getHarvestImage().get(i).getHarvestImageName();

                    String changeName = UUID.randomUUID() + "harvest.png";

                    String harvestImagePath = s3HarvestImageFile.upload(harvestByteImage, changeName, "images");

                    HarvestImage harvestImage = new HarvestImage(
                            harvestImagePath,
                            originalName,
                            changeName,
                            harvest
                    );

                    harvestImagePathList.add(harvestImagePath);

                }

                responseHarvestDto.setHarvestImagePath(harvestImagePathList);

                return responseHarvestDto;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseHarvestDto;
    }

}