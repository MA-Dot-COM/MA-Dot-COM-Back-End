package com.sorhive.comprojectserver.lifing.command.application.dto;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : ResponseLifingDto
 * Comment: 라이핑 생성 응답 전송 객체
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
public class ResponseLifingDto {

    private Long lifingId;
    private String lifingContent;
    private String lifingImagePath;
    private Long lifingNo;
    private Long lifingCategoryNo;
    private Timestamp lifingCreateTime;
    private LifingWriter lifingWriter;

}
