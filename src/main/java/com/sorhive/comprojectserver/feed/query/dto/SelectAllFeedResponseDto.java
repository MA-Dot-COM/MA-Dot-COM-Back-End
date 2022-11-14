package com.sorhive.comprojectserver.feed.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Class : SelectAllFeedResponseDto
 * Comment: 전체 피드 조회에 대한 응답 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-14       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class SelectAllFeedResponseDto {
    
    private int feedCount;
    private List<FeedSummary> feedSummary;
    
}
