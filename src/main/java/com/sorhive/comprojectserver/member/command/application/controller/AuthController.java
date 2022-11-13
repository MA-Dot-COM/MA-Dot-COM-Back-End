package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.dto.LoginDto;
import com.sorhive.comprojectserver.member.command.application.dto.SignUpDto;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * Class : AuthController
 * Comment: 인증 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-06       부시연           최초 생성
 * 2022-11-06       부시연           회원 가입
 * 2022-11-06       부시연           로그인
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** 회원가입 */
    @PostMapping("signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignUpDto signUpDto) {

        log.info("[AuthController] signup Start ======================");
        log.info("[AuthController] signUpDto : " + signUpDto);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원가입 성공", authService.signup(signUpDto)));
    }

    /** 로그인 */
    @PutMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        log.info("[AuthController] login Start ======================");
        log.info("[AuthController] loginDto : " + loginDto);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.NO_CONTENT, "로그인 성공", authService.login(loginDto)));
        
    }
}
