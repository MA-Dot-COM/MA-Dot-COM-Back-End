package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.config.file.S3LifingImageFile;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.ResponseLifingImageAiDto;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage.LifingImage;
import com.sorhive.comprojectserver.lifing.command.domain.repository.LifingImageRepository;
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
public class LifingImageService {

    private static final Logger log = LoggerFactory.getLogger(LifingImageService.class);
    private final S3LifingImageFile s3LifingImageFile;
    private final LifingImageRepository lifingImageRepository;
    private final TokenProvider tokenProvider;

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
                LifingImage lifingImage = new LifingImage(
                        s3LifingImageFile.upload(lifingImageDto.getLifingImage(), changeName, "images"),
                        lifingImageDto.getLifingImageName(),
                        changeName
                );
                lifingImageRepository.save(lifingImage);

                Optional<LifingImage> imagePath = lifingImageRepository.findById(memberCode);

                HttpHeaders headers = new HttpHeaders();

                Charset utf8 = Charset.forName("UTF-8");

                MediaType mediaType = new MediaType("application", "json", utf8);

                headers.setContentType(mediaType);

                Map<String, Object> map = new HashMap<>();
                String path = imagePath.get().getPath();
                map.put("url", path);

                JSONObject params = new JSONObject(map);

                System.out.println(params);

                HttpEntity<JSONObject> requestEntity
                        = new HttpEntity<>(params, headers);

                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<ResponseLifingImageAiDto> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ResponseLifingImageAiDto.class);

                log.info("[LifingImageService] insertImage End ===============");

                return res.getBody();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseLifingImageAiDto();
    }

}
