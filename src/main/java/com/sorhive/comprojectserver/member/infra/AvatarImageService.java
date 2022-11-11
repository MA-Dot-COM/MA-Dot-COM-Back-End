package com.sorhive.comprojectserver.member.infra;

import com.sorhive.comprojectserver.config.file.S3AvatarImageFile;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarCreateDto;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarImageDto;
import com.sorhive.comprojectserver.member.command.application.dto.ResponseAvatarImageAiDto;
import com.sorhive.comprojectserver.member.command.domain.model.avatar.Avatar;
import com.sorhive.comprojectserver.member.command.domain.model.avatarimage.AvatarImage;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarImageRepository;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarRepository;
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
 * Class : AvatarImageService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * 2022-11-11       부시연           바이트배열로 대응
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
@RequiredArgsConstructor
public class AvatarImageService {

    private static final Logger log = LoggerFactory.getLogger(AvatarImageService.class);
    private final S3AvatarImageFile s3AvatarImageFile;
    private final AvatarImageRepository avatarImageRepository;
    private final TokenProvider tokenProvider;

    @Value("${url.avatar}")
    private String url;

    @Transactional
    public ResponseAvatarImageAiDto insertAvatarImage(String accessToken, AvatarImageDto avatarImageDto) {

        log.info("[AvatarService] insertAvatarImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + avatarImageDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        String changeName = avatarImageDto.getAvatarImageName() + "avatar_" + memberCode + ".png";

        try {
            if (avatarImageDto.getAvatarImage() != null) {
                AvatarImage avatarImage = new AvatarImage(
                        memberCode,
                        s3AvatarImageFile.upload(avatarImageDto.getAvatarImage(), changeName, "images"),
                        avatarImageDto.getAvatarImageName(),
                        changeName
                );
                avatarImageRepository.save(avatarImage);

                Optional<AvatarImage> imagePath = avatarImageRepository.findById(memberCode);

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

                ResponseEntity<ResponseAvatarImageAiDto> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ResponseAvatarImageAiDto.class);

                log.info("[AvatarService] insertImage End ===============");

                return res.getBody();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseAvatarImageAiDto();
    }

}
