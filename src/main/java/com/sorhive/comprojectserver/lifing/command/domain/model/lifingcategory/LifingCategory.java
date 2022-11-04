package com.sorhive.comprojectserver.lifing.command.domain.model.lifingcategory;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <pre>
 * Class : StoryCategory
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
@Entity
@Table(name = "tbl_lifing_category")
public class LifingCategory {

    @EmbeddedId
    private LifingCategoryId id;

    @Column(name = "lifing_category_no")
    private Long no;

    @Column(name = "lifing_category_name")
    private String name;

    @Column(name = "lifing_category_path")
    private String path;

}
