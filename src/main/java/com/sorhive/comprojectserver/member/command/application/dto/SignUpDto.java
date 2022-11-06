package com.sorhive.comprojectserver.member.command.application.dto;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberId;
import com.sorhive.comprojectserver.member.command.domain.model.member.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <pre>
 * Class : SignUpDto
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
public class SignUpDto {

    private String memberId;

//    @NotBlank(message = "닉네임을 입력헤주세요")
    private String memberName;

    private String password;

    protected SignUpDto() {}

    public SignUpDto(String memberId, String memberName, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }
}
