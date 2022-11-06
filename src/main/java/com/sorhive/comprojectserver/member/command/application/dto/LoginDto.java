package com.sorhive.comprojectserver.member.command.application.dto;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberId;
import com.sorhive.comprojectserver.member.command.domain.model.member.Password;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

    @NotBlank(message = "ID는 필수 값입니다.")
    private MemberId memberId;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private Password password;

    protected LoginDto() { }

    public LoginDto(MemberId memberId, Password password) {
        this.memberId = memberId;
        this.password = password;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public Password getPassword() {
        return password;
    }

}
