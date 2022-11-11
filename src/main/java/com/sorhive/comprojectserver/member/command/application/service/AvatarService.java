package com.sorhive.comprojectserver.member.command.application.service;

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
 * 2022-11-11       부시연           바이트배열로 대응
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
@RequiredArgsConstructor
public class AvatarService {

    private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long createAvatar(String accessToken, AvatarCreateDto avatarCreateDto) {

        log.info("[AvatarService] createAvatar Start ===============");
        log.info("[AvatarService] avatarCreateDto : " + avatarCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Avatar avatar = new Avatar(
                memberCode,
                avatarCreateDto.getFaceType(),
                avatarCreateDto.getEyeType(),
                avatarCreateDto.getEyeBrowsType(),
                avatarCreateDto.getHairType()
        );

        avatarRepository.save(avatar);

        return memberCode;
    }
}
