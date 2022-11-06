package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.dto.LoginDto;
import com.sorhive.comprojectserver.member.command.application.dto.SignUpDto;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
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
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignUpDto signUpDto) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원가입 성공", authService.signup(signUpDto)));
    }

    @PutMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", authService.login(loginDto)));
        
    }
}
