package com.sorhive.comprojectserver.feed.query.dto;

import lombok.Getter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : FeedCommentSummary
 * Comment: 피드 댓글 전송 객체
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
public class FeedCommentSummary {

    private Long feedCommentId;
    private String feedCommentContent;
    private String feedCommentWriterName;
    private Character feedCommentDeleteYn;
    private Timestamp feedCommentCreateTime;

}
