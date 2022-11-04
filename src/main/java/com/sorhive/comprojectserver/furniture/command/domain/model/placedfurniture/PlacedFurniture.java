package com.sorhive.comprojectserver.furniture.command.domain.model.placedfurniture;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : PlacedFurniture
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
@Table(name = "tbl_placed_furnitures")
public class PlacedFurniture {

    @EmbeddedId
    private PlacedFurnitureId Id;

    @Column(name = "placed_furniture_create_time")
    private Timestamp createTime;

    @Column(name = "placed_furniture_upload_time")
    private Timestamp uploadTime;

    @Column(name = "placed_furniture_delete_time")
    private Timestamp deleteTime;

    @Column(name = "placed_furniture_delete_yn")
    private Character deleteYn;
}
