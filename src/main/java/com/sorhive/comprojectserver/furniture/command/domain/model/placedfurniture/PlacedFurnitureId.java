package com.sorhive.comprojectserver.furniture.command.domain.model.placedfurniture;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : PlacedFurnitureId
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
@Embeddable
@Access(AccessType.FIELD)
public class PlacedFurnitureId implements Serializable {

    @Column(name="placed_furniture_id")
    private Long value;

    public PlacedFurnitureId() {
    }

    protected PlacedFurnitureId(Long value) { this.value = value; }

    public  Long getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacedFurnitureId that = (PlacedFurnitureId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static PlacedFurnitureId of(Long value) {
        return new PlacedFurnitureId(value);
    }
}
