package com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
//
//    @Embedded
//    private HarvestWriter harvestWriter;

    protected HarvestComment() {}

    public HarvestComment(HarvestCommentId id, String content, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn, Harvest harvest) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
        this.harvest = harvest;
    }

    public HarvestCommentId getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public Character getDeleteYn() {
        return deleteYn;
    }

    public Harvest getHarvest() { return harvest; }

}
