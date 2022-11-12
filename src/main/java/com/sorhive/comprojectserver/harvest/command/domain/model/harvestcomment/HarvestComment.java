package com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : HarvestComment
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
@Getter
public class HarvestComment {

    @Id
    @Column(name="harvest_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "harvest_comment_content")
    private String content;

    private HarvestCommentWriter harvestCommentWriter;

    @Column(name = "harvest_comment_create_time")
    private Timestamp createTime;

    @Column(name = "harvest_comment_upload_time")
    private Timestamp uploadTime;

    @Column(name = "harvest_comment_delete_time")
    private Timestamp deleteTime;

    @Column(name = "harvest_comment_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    protected HarvestComment() {}

    public HarvestComment(HarvestCommentWriter harvestCommentWriter, String harvestCommentContent, Harvest harvest) {
        this.harvestCommentWriter = harvestCommentWriter;
        this.content = harvestCommentContent;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
        this.deleteYn = 'N';
        this.harvest = harvest;
    }
}
