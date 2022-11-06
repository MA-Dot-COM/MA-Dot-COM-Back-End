package com.sorhive.comprojectserver.member.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.member.command.application.dto.LoginDto;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", authService.login(loginDto)));
        
    }
}
