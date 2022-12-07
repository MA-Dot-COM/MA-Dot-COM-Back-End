package com.sorhive.comprojectserver.member.query.follow;

import com.sorhive.comprojectserver.common.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <pre>
 * Class : FollowQueryController
 * Comment: 팔로우 조회 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * 2022-11-20       부시연           팔로우 목록 조회 수정
 * 2022-11-20       부시연           팔로잉 목록 조회 수정
 * 2022-12-06       부시연           팔로워 목록 검색 기능 추가
 * 2022-12-06       부시연           팔로잉 목록 검색 기능 추가
 * 2022-12-07       부시연           팔로워 목록 페이징 기능 추가
 * 2022-12-07       부시연           팔로잉 목록 페이징 기능 추가
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

    /** 팔로워 목록 조회 */
    @GetMapping("follower/{memberCode}")
    public ResponseEntity<ResponseDto> findFollowerList(@PathVariable Long memberCode, @RequestBody Optional<FollowerRequestDto> followerRequestDto) {

        log.info("[FollowQueryController] findFollowerList Start ==================");

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로워 목록 조회 성공", followQueryService.findFollowerList(memberCode, followerRequestDto)));
    }

    /** 팔로잉 목록 조회 */
    @GetMapping("following/{memberCode}")
    public ResponseEntity<ResponseDto> findFollowingList(@PathVariable Long memberCode, @RequestBody Optional<FollowingRequestDto> followingRequestDto) {

        log.info("[FollowQueryController] findFollowingList Start ==================");

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로잉 목록 조회 성공", followQueryService.findFollowingList(memberCode, followingRequestDto)));
    }

    /** 팔로워 검색 */
    @GetMapping("follower/search/{memberId}")
    public ResponseEntity<ResponseDto> searchFollowerById(@RequestHeader String Authorization, @PathVariable String memberId) {

        log.info("[FollowQueryController] searchFollowerById Start ==================");

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로워 검색 성공", followQueryService.searchFollowerById(memberId, accessToken)));
    }

    /** 팔로잉 검색 */
    @GetMapping("following/search/{memberId}")
    public ResponseEntity<ResponseDto> searchFolloingById(@RequestHeader String Authorization, @PathVariable String memberId) {

        log.info("[FollowQueryController] searchFollowingById Start ==================");

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "팔로잉 검색 성공", followQueryService.searchFollowingById(memberId, accessToken)));
    }

}
