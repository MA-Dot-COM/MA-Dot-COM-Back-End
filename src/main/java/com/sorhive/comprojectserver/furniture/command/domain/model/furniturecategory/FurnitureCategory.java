package com.sorhive.comprojectserver.furniture.command.domain.model.furniturecategory;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "tbl_furniture_category")
public class FurnitureCategory {
    @EmbeddedId
    private FurnitureCategoryId id;

    @Column(name = "furniture_category_name")
    private String name;

    protected FurnitureCategory() {
    }

    public FurnitureCategory(FurnitureCategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public FurnitureCategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
