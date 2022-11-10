package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : FollowController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1/follow")
public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{followId}")
    public ResponseEntity<ResponseDto> createAvatar(@RequestHeader String Authorization, @PathVariable String followId ) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로우 추가 성공", followService.createFollow(accessToken, followId)));

    }
}
