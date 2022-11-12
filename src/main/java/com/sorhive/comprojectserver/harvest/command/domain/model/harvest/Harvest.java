package com.sorhive.comprojectserver.harvest.command.domain.model.harvest;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.HarvestComment;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvestimage.HarvestImage;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : Harvest
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
@Getter
public class Harvest {

    @Id
    @Column(name="harvest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long harvestId;

    @Column(name = "harvest_content")
    private String harvestContent;

    private HarvestWriter harvestWriter;

    @Column(name = "harvest_create_time")
    private Timestamp createTime;

    @Column(name = "harvest_upload_time")
    private Timestamp uploadTime;

    @Column(name = "harvest_delete_time")
    private Timestamp deleteTime;

    @Column(name = "harvest_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    protected List<HarvestComment> harvestComments = new ArrayList<>();

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    protected List<HarvestImage> harvestImages = new ArrayList<>();

    protected Harvest() { }

    public Harvest(HarvestWriter harvestWriter, String harvestContent) {

        this.harvestWriter = harvestWriter;
        this.harvestContent = harvestContent;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
        this.deleteYn = 'N';

    }
}
