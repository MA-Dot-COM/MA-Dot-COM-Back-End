package com.sorhive.comprojectserver.member.command.application.dto;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberId;

/**
 * <pre>
 * Class : LoginDto
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
public class LoginDto {

    private MemberId memberId;

    private String password;

    protected LoginDto() { }

    public LoginDto(MemberId memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }
}
