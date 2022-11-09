package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.file.S3File;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarCreateDto;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarImageDto;
import com.sorhive.comprojectserver.member.command.application.dto.ResponseAvatarImageAiDto;
import com.sorhive.comprojectserver.member.command.domain.model.avatarimage.AvatarImage;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarImageRepository;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarRepository;
import org.apache.commons.io.FilenameUtils;
import org.bson.json.JsonObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <pre>
 * Class : AvatarService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class AvatarService {

    private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
    private final S3File s3File;
    private final AvatarRepository avatarRepository;
    private final AvatarImageRepository avatarImageRepository;
    private final TokenProvider tokenProvider;

    @Value("${url.avatar}")
    private String url;

    public AvatarService(S3File s3File, AvatarRepository avatarRepository, AvatarImageRepository avatarImageRepository, TokenProvider tokenProvider) {
        this.s3File = s3File;
        this.avatarRepository = avatarRepository;
        this.avatarImageRepository = avatarImageRepository;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public String insertAvatarImage(String accessToken, AvatarImageDto avatarImageDto) {

        log.info("[AvatarService] insertImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + avatarImageDto);
        String changeName = UUID.randomUUID().toString().replace("-", "");

        System.out.println("sdalfj;lskajdfl;sdjkf");
        System.out.println(accessToken);
        System.out.println("sdalfj;lskajdfl;sdjkf");
        System.out.println(tokenProvider.getUserCode(accessToken));
        System.out.println("DLFJsd;lkfjsdlfj");
        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        System.out.println(memberCode);

        System.out.println("sdalfj;lskajdfl;sdjkf");

        String ext = FilenameUtils.getExtension(avatarImageDto.getAvatarImage().getResource().getFilename());

        try {
            if (avatarImageDto.getAvatarImage() != null) {
                AvatarImage avatarImage = new AvatarImage(
                        memberCode,
                        s3File.upload(avatarImageDto.getAvatarImage(), changeName + "." + ext, "images"),
                        avatarImageDto.getAvatarImage().getResource().getFilename(),
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

                ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);


                log.info("[AvatarService] insertImage End ===============");

                return res.getBody();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    @Transactional
    public String createAvatar(AvatarCreateDto avatarCreateDto) {

        log.info("[AvatarService] insertImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + avatarCreateDto);

        int result = 0;

        return (result > 0) ? "아바타 생성 성공" : "아바타 생성 실패";
    }
}
