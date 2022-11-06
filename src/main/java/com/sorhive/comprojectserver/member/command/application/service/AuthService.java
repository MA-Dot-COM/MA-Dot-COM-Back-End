package com.sorhive.comprojectserver.member.command.application.service;

import com.sorhive.comprojectserver.config.dto.TokenDto;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.dto.LoginDto;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import com.sorhive.comprojectserver.member.exception.LoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class : AuthService
 * Comment: 클래스에 대한 간단 설명
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
@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }


    @Transactional
    public TokenDto login(LoginDto loginDto) {
        log.info("[AuthService] Login Start ===================================");
        log.info("[AuthService] {}", loginDto);

        // 1. 아이디 조회
        Member member = memberRepository.findByMemberId(loginDto.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));

        // 2. 비밀번호 매칭
        if (!passwordEncoder.matches(loginDto.getPassword().toString(), member.getPassword().toString())) {
            log.info("[AuthService] Password Match Fail!!!!!!!!!!!!");
            throw new LoginFailedException("잘못된 아이디 또는 비밀번호입니다");
        }

        // 3. 토큰 발급
        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        log.info("[AuthService] tokenDto {}", tokenDto);

        log.info("[AuthService] Login End ===================================");

        return tokenDto;

    }

}
