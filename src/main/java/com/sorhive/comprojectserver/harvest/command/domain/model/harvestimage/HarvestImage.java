package com.sorhive.comprojectserver.harvest.command.domain.model.harvestimage;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;

import javax.persistence.*;

/**
 * <pre>
 * Class : HarvestImage
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
@Table(name = "tbl_harvest_images")
public class HarvestImage {

    @Id
    @Column(name="harvest_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "harvest_image_path")
    private String path;

    @Column(name = "harvest_original_name")
    private String orginalName;

    @Column(name = "lifing_saved_name")
    private String savedName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    protected HarvestImage() { }

    public HarvestImage(String harvestImagePath, String originalName, String changeName, Harvest harvestId) {

        this.path = harvestImagePath;
        this.orginalName = originalName;
        this.savedName = changeName;
        this.harvest = harvest;
    }
}
