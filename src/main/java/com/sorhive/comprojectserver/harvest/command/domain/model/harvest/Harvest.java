package com.sorhive.comprojectserver.harvest.command.domain.model.harvest;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Feed
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
@Table(name = "tbl_harvests")
public class Harvest {

    @EmbeddedId
    private HarvestId harvestId;

    @Column(name = "harvest_content")
    private String harvestContent;

    @Column(name = "harvest_create_time")
    private Timestamp createTime;

    @Column(name = "harvest_upload_time")
    private Timestamp uploadTime;

    @Column(name = "harvest_delete_time")
    private Timestamp deleteTime;

    @Column(name = "harvest_delete_yn")
    private Character deleteYn;


    protected Harvest() {
    }


    public HarvestId getHarvestId() {
        return harvestId;
    }

    public String getHarvestContent() {
        return harvestContent;
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

}
