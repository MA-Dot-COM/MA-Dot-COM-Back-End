package com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <pre>
 * Class : LifingCommentWriter
 * Comment: 라이핑 댓글 작성자 도메인
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-15       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
@Getter
@AllArgsConstructor
public class LifingCommentWriter {

    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "lifing_comment_writer_code"))
    )
    private MemberCode lifingCommentWriterCode;

    @Column(name = "lifing_comment_writer_name")
    private String lifingCommentWriterName;

    @Column(name = "lifing_comment_writer_id")
    private String lifingCommentWriterId;


    protected LifingCommentWriter() {}

}
