package com.sorhive.comprojectserver.common.pattern;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * <pre>
 * Class : CustomPattern
 * Comment: 사용자 입력 패턴
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-24       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Component
public class CustomPattern {

    /** 아이디 패턴 값 분석 */
    public boolean idPattern(String memberId) {

        boolean isMatching = true;

        if(Pattern.matches("^[_.a-zA-Z0-9]{1,20}$", memberId)) {
            isMatching = true;
        } else {
            isMatching = false;
        }

        return isMatching;
    }

    /** 비밀번호 패턴 값 분석 */
    public boolean passwordPattern(String password) {

        boolean isMatching = true;

        if(Pattern.matches("^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[!@#$%]).{8,20}$", password)) {
            isMatching = true;
        } else {
            isMatching = false;
        }

        return isMatching;
    }

    /** 닉네임 패턴 값 분석 */
    public boolean nicknamePattern(String nickname) {

        boolean isMatching = true;

        if(Pattern.matches("^(?=.*[0-9a-zA-Z가-힣])(?=.*[-.!@#$%]).{1,20}$", nickname)) {
            isMatching = true;
        } else {
            isMatching = false;
        }

        return isMatching;
    }
}
