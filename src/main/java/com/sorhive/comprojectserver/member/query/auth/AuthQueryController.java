package com.sorhive.comprojectserver.member.query.auth;

import com.sorhive.comprojectserver.common.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <pre>
 * Class : AuthQueryController
 * Comment: 인증 조회 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-16       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("/api/v1")
public class AuthQueryController {

    private static final Logger log = LoggerFactory.getLogger(AuthQueryController.class);
    private final AuthQueryService authQueryService;

    public AuthQueryController(AuthQueryService authQueryService) {
        this.authQueryService = authQueryService;
    }


    /** 아이디 검사 */
    @GetMapping("auth/id/{newMemberId}")
    public ResponseEntity<ResponseDto> idCheck(@Valid @PathVariable String newMemberId) {

        log.info("[AuthQueryController] idCheck Start =================");

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "회원 아이디 검사", authQueryService.idCheck(newMemberId)));
    }
}
