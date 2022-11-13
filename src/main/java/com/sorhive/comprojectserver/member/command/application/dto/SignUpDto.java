package com.sorhive.comprojectserver.member.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class : SignUpDto
 * Comment: 회원 생성 전송 객체
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
@AllArgsConstructor
@Getter
public class SignUpDto {

    @NotNull
    private String memberId;

    @NotNull
    private String memberName;

    @NotNull
    private String password;

    protected SignUpDto() {}
}
