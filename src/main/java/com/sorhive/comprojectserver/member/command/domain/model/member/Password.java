package com.sorhive.comprojectserver.member.command.domain.model.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * Class : IdPasswordNotMatchingException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
public class Password {

    @Column(name = "password")
    @NotBlank(message = "비밀번호를 입력헤주세요")
//    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[!@#$%]).{8,20}$")
    private String value;

    protected Password() {
    }

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
