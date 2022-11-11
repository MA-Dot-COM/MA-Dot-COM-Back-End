package com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : LifingImage
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
@Table(name = "tbl_lifing_image")
@DynamicInsert
@DynamicUpdate
@Getter
public class LifingImage {

    @Id
    @Column(name = "lifing_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lifing_image_path")
    private String path;

    @Column(name = "lifing_original_name")
    private String orginalName;

    @Column(name = "lifing_saved_name")
    private String savedName;

    @Column(name = "lifing_saved_name")
    private Timestamp uploadTime;

    protected LifingImage() { }
    public LifingImage(String path, String orginalName, String savedName) {

        this.path = path;
        this.orginalName = orginalName;
        this.savedName = savedName;
        this.uploadTime = new Timestamp(System.currentTimeMillis());

    }

}
