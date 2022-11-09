package com.sorhive.comprojectserver.member.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class : AvatarCreateDto
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarCreateDto {
    private Integer faceType;
    private Integer eyeBrowsType;
    private Integer eyeType;
    private Integer hairType;
}