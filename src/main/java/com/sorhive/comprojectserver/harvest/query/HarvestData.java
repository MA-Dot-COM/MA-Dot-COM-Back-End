package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;

import javax.persistence.*;

/**
 * <pre>
 * Class : HarvestData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_harvests")
public class HarvestData {

    @EmbeddedId
    private HarvestId harvestId;

    @Column(name = "harvest_content")
    private String content;

    @Column(name = "harvest_delete_yn")
    private Character deleteYn;

    protected HarvestData() {}

    public HarvestData(HarvestId harvestId, String content, Character deleteYn) {
        this.harvestId = harvestId;
        this.content = content;
        this.deleteYn = deleteYn;
    }

    public HarvestId getHarvestId() {
        return harvestId;
    }

    public String getContent() {
        return content;
    }

    public Character getDeleteYn() {
        return deleteYn;
    }
}
