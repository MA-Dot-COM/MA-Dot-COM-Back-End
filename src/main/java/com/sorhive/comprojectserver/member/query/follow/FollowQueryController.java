package com.sorhive.comprojectserver.member.query.follow;

import com.sorhive.comprojectserver.common.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Class : FollowQueryController
 * Comment: 팔로우 조회 컨트롤러
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
@RequestMapping("api/v1")
public class FollowQueryController {

    private static final Logger log = LoggerFactory.getLogger(FollowQueryController.class);
    private final FollowQueryService followQueryService;

    public FollowQueryController(FollowQueryService followQueryService) {
        this.followQueryService = followQueryService;
    }

    @GetMapping("follower")
    public ResponseEntity<ResponseDto> findFollowerList(@RequestHeader String Authorization) {

        log.info("[FollowQueryController] findFollowerList Start ==================");

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로워 목록 조회 성공", followQueryService.findFollowerList(accessToken)));
    }

    @GetMapping("following")
    public ResponseEntity<ResponseDto> findFollowingList(@RequestHeader String Authorization) {

        log.info("[FollowQueryController] findFollowingList Start ==================");

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로잉 목록 조회 성공", followQueryService.findFollowingList(accessToken)));
    }
}
