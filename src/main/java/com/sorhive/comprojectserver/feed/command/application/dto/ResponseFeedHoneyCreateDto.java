package com.sorhive.comprojectserver.feed.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : ResponseFeedHoneyCreateDto
 * Comment: 허니 생성 응답 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-15       부시연           라이핑과의 구분을 위해 피드 허니로 변경
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class ResponseFeedHoneyCreateDto {

    private Long feedHoneyId;
    private Long memberCode;
    private Long feedId;
    private Timestamp createTime;

}