package com.sorhive.comprojectserver.feed.query.dto;

import lombok.Getter;

/**
 * <pre>
 * Class : FeedImageSummary
 * Comment: 피드 이미지 전송 객체
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
public class FeedImageSummary {

    private Long feedImageId;
    private String feedImagePath;
    private String feedImageOriginalName;

}
