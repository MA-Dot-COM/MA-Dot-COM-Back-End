package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarCreateDto;
import com.sorhive.comprojectserver.member.command.application.dto.AvatarImageDto;
import com.sorhive.comprojectserver.member.command.application.service.AvatarService;
import com.sorhive.comprojectserver.member.infra.AvatarImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : AvatarContoller
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
@RestController
@RequestMapping("api/v1/avatar")
public class AvatarContoller {

    private AvatarService avatarService;

    private AvatarImageService avatarImageService;

    public AvatarContoller(AvatarService avatarService, AvatarImageService avatarImageService) {
        this.avatarService = avatarService;
        this.avatarImageService = avatarImageService;
    }

    @PostMapping(value = "image")
    public ResponseEntity<ResponseDto> avatarImage(@RequestHeader String Authorization, @RequestBody AvatarImageDto avatarImageDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "아바타 이미지 생성 성공", avatarImageService.insertAvatarImage(accessToken, avatarImageDto)));

    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> createAvatar(@RequestHeader String Authorization, @RequestBody AvatarCreateDto avatarCreateDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "아바타 생성 성공", avatarService.createAvatar(accessToken, avatarCreateDto)));

    }
}
