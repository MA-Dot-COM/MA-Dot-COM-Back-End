package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.file.S3File;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarCreateDto;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarImageDto;
import com.sorhive.comprojectserver.member.command.domain.model.avatarimage.AvatarImage;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarImageRepository;
import com.sorhive.comprojectserver.member.command.domain.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

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
@RequiredArgsConstructor
public class AvatarService {

    private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
    private final S3File s3File;
    private final AvatarRepository avatarRepository;
    private final AvatarImageRepository avatarImageRepository;

    @Value("${url.ai}")
    private String url;

    @Transactional
    public Long insertAvatarImage(AvatarImageDto avatarImageDto) {

        log.info("[AvatarService] insertImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + avatarImageDto);
        String changeName = UUID.randomUUID().toString().replace("-", "");

        String ext = FilenameUtils.getExtension(avatarImageDto.getAvatarImage().getResource().getFilename());

        try {
            if (avatarImageDto.getAvatarImage() != null) {
                AvatarImage avatarImage = new AvatarImage(
                        s3File.upload(avatarImageDto.getAvatarImage(), changeName + "." + ext, "images"),
                        avatarImageDto.getAvatarImage().getResource().getFilename(),
                        changeName
                );
                avatarImageRepository.save(avatarImage);


//                HttpHeaders headers = new HttpHeaders();
//
//                Charset utf8 = Charset.forName("UTF-8");
//
//                MediaType mediaType = new MediaType("application", "json", utf8);
//
//                headers.setContentType(mediaType);
//
//                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//
//                HttpEntity<MultiValueMap<String, String>> requestEntity
//                        = new HttpEntity<>(params, headers);
//
//                RestTemplate restTemplate = new RestTemplate();

//                ResponseEntity<String> res = restTemplate.postForEntity(url, requestEntity, String.class);

                log.info("[AvatarService] insertImage End ===============");

                return avatarImage.getId();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    @Transactional
    public String createAvatar(AvatarCreateDto avatarCreateDto) {

        log.info("[AvatarService] insertImage Start ===============");
        log.info("[AvatarService] avatarImageDto : " + avatarCreateDto);

        int result = 0;

        return (result > 0) ? "아바타 생성 성공" : "아바타 생성 실패";
    }
}
