package com.sorhive.comprojectserver.lifing.query.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : LifingCommentSummary
 * Comment: 피드 댓글 전송 객체
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
@Entity
@Table(name = "tbl_lifing_comments")
@Getter
public class LifingCommentData {

    @Id
    @Column(name="lifing_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lifingCommentId;

    @Column(name = "lifing_comment_content")
    private String lifingCommentContent;


    @Column(name = "lifing_comment_writer_name")
    private String lifingCommentWriterName;

    @Column(name = "lifing_comment_writer_id")
    private String lifingCommentWriterId;

    @Column(name = "lifing_comment_delete_yn")
    private Character lifingCommentDeleteYn;
    @Column(name = "lifing_comment_create_time")
    private Timestamp lifingCommentCreateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_lifing_id")
    private Lifing lifing;

    protected LifingCommentData() { }

}
