package com.sorhive.comprojectserver.lifing.command.domain.model.lifingcategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : StoryCategoryId
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
@Access(AccessType.FIELD)
public class LifingCategoryId implements Serializable {

    @Column(name="lifing_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long value;

    protected LifingCategoryId() {
    }

    public LifingCategoryId(Long value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifingCategoryId that = (LifingCategoryId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static LifingCategoryId of(Long value) { return new LifingCategoryId(value); }

}