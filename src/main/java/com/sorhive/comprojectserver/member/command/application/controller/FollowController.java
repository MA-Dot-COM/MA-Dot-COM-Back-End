package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 2022-11-10       부시연           팔로우 추가
 * 2022-11-11       부시연           팔로우 삭제
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class FollowController {

    private static final Logger log = LoggerFactory.getLogger(FollowController.class);
    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    /** 팔로우 추가 */
    @PostMapping("follow/{followId}")
    public ResponseEntity<ResponseDto> createFollow(@RequestHeader String Authorization, @PathVariable Long followId ) {

        log.info("[FollowController] createFollow Start ==================== " );
        log.info("[FollowController] followId : " + followId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "팔로우 추가 성공", followService.createFollow(accessToken, followId)));

    }

    /** 팔로우 삭제 */
    @DeleteMapping("follow/{followId}")
    public ResponseEntity<ResponseDto> deleteFollow(@PathVariable Long followId ) {

        log.info("[FollowController] deleteFollow Start ==================== " );
        log.info("[FollowController] followId : " + followId);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.NO_CONTENT, "팔로우 삭제 성공", followService.deleteFollow(followId)));

    }
}
