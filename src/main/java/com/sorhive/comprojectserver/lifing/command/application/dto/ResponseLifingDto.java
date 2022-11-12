package com.sorhive.comprojectserver.lifing.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : ResponseLifingDto
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 * @version 1(클래스 버전)
 * @author 부시연(최초 작성자)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseLifingDto {
    private Long lifingId;
    private String lifingContent;
    private String lifingImagePath;
    private Long lifingNo;
    private Timestamp lifingCreateTime;
}
