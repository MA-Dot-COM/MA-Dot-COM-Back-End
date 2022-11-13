package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.file.S3LifingImageFile;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingImageAiDto;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingImageRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingRepository;
import com.sorhive.comprojectserver.lifing.query.LifingImageMapper;
import com.sorhive.comprojectserver.lifing.query.LifingImagePath;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 * Class : LifingImageService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@RequiredArgsConstructor
public class LifingService {

    private static final Logger log = LoggerFactory.getLogger(LifingService.class);
    private final S3LifingImageFile s3LifingImageFile;
    private final LifingImageRepository lifingImageRepository;
    private final LifingRepository lifingRepository;

    private final LifingWriterService lifingWriterService;
    private final TokenProvider tokenProvider;
    private final LifingImageMapper lifingImageMapper;
    private final MemberRepository memberRepository;

    @Value("${url.lifing}")
    private String url;

    @Transactional
    public ResponseLifingImageAiDto insertLifingImage(String accessToken, LifingImageDto lifingImageDto) {

        log.info("[LifingImageService] insertLifingImage Start ===============");
        log.info("[LifingImageService] lifingImageDto : " + lifingImageDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        String changeName = lifingImageDto.getLifingImage() + "lifing_" + memberCode + ".png";

        try {
            if (lifingImageDto.getLifingImage() != null) {

                String lifingPath = s3LifingImageFile.upload(lifingImageDto.getLifingImage(), changeName, "images");

                HttpHeaders headers = new HttpHeaders();

                Charset utf8 = Charset.forName("UTF-8");

                MediaType mediaType = new MediaType("application", "json", utf8);

                headers.setContentType(mediaType);

                Map<String, Object> map = new HashMap<>();
                map.put("url", lifingPath);

                JSONObject params = new JSONObject(map);

                System.out.println(params);

                HttpEntity<JSONObject> requestEntity = new HttpEntity<>(params, headers);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<ResponseLifingImageAiDto> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ResponseLifingImageAiDto.class);

                LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

                Long analyzedLifingNo = res.getBody().getLifing();

                LifingImage lifingImage = new LifingImage(
                        lifingPath,
                        lifingImageDto.getLifingImageName(),
                        changeName,
                        analyzedLifingNo,
                        lifingWriter
                );
                lifingImageRepository.save(lifingImage);

                log.info("[LifingImageService] insertImage End ===============");

                return res.getBody();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseLifingImageAiDto();
    }

    public Object createLifing(String accessToken, LifingCreateDto lifingCreateDto) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        LifingWriter lifingWriter = lifingWriterService.createLifingWriter(new MemberCode(memberCode));

        Long lifingNo = lifingCreateDto.getLifingNo();
        String lifingConetent = lifingCreateDto.getLifingContent();

        LifingImagePath lifingImagePath = lifingImageMapper.findLifingImageByMemberCode(memberCode);

        Lifing lifing = new Lifing(
                lifingWriter,
                lifingNo,
                lifingConetent,
                lifingImagePath.getLifingPath()
        );

        lifingRepository.save(lifing);

        Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
        Member member = memberData.get();
        member.setLifingNo(lifingNo);
        memberRepository.save(member);

        ResponseLifingDto responseLifingDto = new ResponseLifingDto();

        responseLifingDto.setLifingId(lifing.getLifingId());
        responseLifingDto.setLifingContent(lifing.getLifingConetent());
        responseLifingDto.setLifingImagePath(lifing.getLifingImagePath());
        responseLifingDto.setLifingNo(lifing.getLifingNo());
        responseLifingDto.setLifingCreateTime(lifing.getCreateTime());

        return responseLifingDto;
    }
}
