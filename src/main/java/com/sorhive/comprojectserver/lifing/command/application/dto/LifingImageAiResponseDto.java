package com.sorhive.comprojectserver.lifing.command.application.dto;

import lombok.Getter;

/**
 * <pre>
 * Class : LifingImageAiResponseDto
 * Comment: AI 서버에서 돌아오는 응답 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class LifingImageAiResponseDto {
    private Long space;

    protected LifingImageAiResponseDto() {}
    public LifingImageAiResponseDto(Long analyzedLifingNo) {

        this.space = analyzedLifingNo;

    }
}
