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

    @GetMapping("member/{memberId}")
    public ResponseEntity<ResponseDto> findMemberByMemberId(@PathVariable String memberId) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 ID 검색 성공", memberQueryService.findMemberByMemberId(memberId)));
    }

    @GetMapping("member/list/{page}")
    public ResponseEntity<ResponseDto> findAllByMemberCode(@RequestHeader String Authorization, @PathVariable Long page) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 목록 조회 성공", memberQueryService.findAllByMemberCode(accessToken, page)));
    }
}
