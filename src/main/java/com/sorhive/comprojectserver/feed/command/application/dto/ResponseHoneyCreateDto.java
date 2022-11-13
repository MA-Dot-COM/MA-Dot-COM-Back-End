package com.sorhive.comprojectserver.feed.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : ResponseHoneyDto
 * Comment: 허니 생성 응답 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class ResponseHoneyCreateDto {

    private Long honeyId;
    private Long memberCode;
    private Long feedId;
    private Timestamp createTime;

}
