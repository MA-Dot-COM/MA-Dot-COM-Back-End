package com.sorhive.comprojectserver.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.sorhive.comprojectserver.config.security.WebSecurityConfig.AUTHCOOKIENAME;

/**
 * <pre>
 * Class : CustomAuthSuccessHandler
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        try {
            Cookie authCookie = new Cookie(AUTHCOOKIENAME, URLEncoder.encode(encryptId(user), "UTF-8"));
            authCookie.setPath("/");
            response.addCookie(authCookie);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        response.sendRedirect("/home");
    }

    private String encryptId(UserDetails user) {
        return user.getUsername();
    }
}
