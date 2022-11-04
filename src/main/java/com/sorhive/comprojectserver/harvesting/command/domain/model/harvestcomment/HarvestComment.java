package com.sorhive.comprojectserver.harvesting.command.domain.model.harvestcomment;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : FeedComment
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_harvest_comments")
public class HarvestComment {

    @EmbeddedId
    private HarvestCommentId id;

    @Column(name = "harvest_comment_content")
    private String content;

    @Column(name = "harvest_comment_create_time")
    private Timestamp createTime;

    @Column(name = "harvest_comment_upload_time")
    private Timestamp uploadTime;

    @Column(name = "harvest_comment_delete_time")
    private Timestamp deleteTime;

    @Column(name = "harvest_comment_delete_yn")
    private Character deleteYn;
}
