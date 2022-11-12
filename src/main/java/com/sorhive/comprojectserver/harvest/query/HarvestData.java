package com.sorhive.comprojectserver.harvest.query;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

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
@Getter
public class HarvestData {

    @Id
    @Column(name="harvest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long harvestId;

    @Column(name = "harvest_content")
    private String content;

    @Column(name = "harvest_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    protected HarvestData() {}
}
