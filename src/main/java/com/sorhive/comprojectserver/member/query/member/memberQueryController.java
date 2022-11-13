package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : memberQueryController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           회원 목록 조회 추가
 * 2022-11-11       부시연           회원 검색 추가
 * 2022-11-12       부시연           회원 검색 수정
 * 2022-11-13       부시연           룸인 전용 자신을 포함한 랜덤 회원 7명 조회 기능 추가
 * 2022-11-13       부시연           자신을 제외한 회원 목록 조회 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class memberQueryController {

    private final MemberQueryService memberQueryService;

    public memberQueryController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @GetMapping("member/id/{memberId}")
    public ResponseEntity<ResponseDto> findMemberByMemberId(@RequestHeader String Authorization, @PathVariable String memberId) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 ID 검색 성공", memberQueryService.findMemberByMemberId(accessToken, memberId)));
    }

    @GetMapping("member/list")
    public ResponseEntity<ResponseDto> findAllMember(@RequestHeader String Authorization) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 ID 검색 성공", memberQueryService.findAllMember(accessToken)));
    }

    @GetMapping("member/list/{page}")
    public ResponseEntity<ResponseDto> findAllByMemberCode(@RequestHeader String Authorization, @PathVariable Long page) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 목록 조회 성공", memberQueryService.findAllByMemberCode(accessToken, page)));
    }

    @GetMapping("member/roomin")
    public ResponseEntity<ResponseDto> findRandomMember(@RequestHeader String Authorization) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 랜덤 조회 성공", memberQueryService.findRandomMember(accessToken)));
    }
}
