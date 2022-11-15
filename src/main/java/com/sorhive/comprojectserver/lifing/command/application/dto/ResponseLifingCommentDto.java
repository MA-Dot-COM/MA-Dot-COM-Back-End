package com.sorhive.comprojectserver.lifing.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : ResponseLifingCommentDto
 * Comment: 라이핑 댓글 응답 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-15       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class ResponseLifingCommentDto {

    private Long lifingCommentId;
    private String lifingCommentcontent;
    private Timestamp lifingCommentCreateTime;

}
