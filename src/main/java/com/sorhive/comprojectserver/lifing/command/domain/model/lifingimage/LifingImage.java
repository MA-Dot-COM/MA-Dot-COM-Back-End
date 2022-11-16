package com.sorhive.comprojectserver.lifing.command.domain.model.lifingimage;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.Lifing;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : LifingImage
 * Comment: 라이핑 이미지 도메인 모델
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       부시연           최초 생성
 * 2022-11-15       부시연           분석된 라이핑 이미지 컬럼 제거 && 라이핑 컬럼 연관관계 매핑
 * 2022-11-17       부시연           라이핑 이미지 삭제여부 및 삭제 시간 추가
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

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_time")
    private Timestamp deleteTime;

    @Column(name = "lifing_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lifing_id")
    private Lifing lifing;

    protected LifingImage() { }
    public LifingImage(String path, String orginalName, String savedName, Lifing lifingId) {

        this.path = path;
        this.orginalName = orginalName;
        this.savedName = savedName;
        this.uploadTime = new Timestamp(System.currentTimeMillis());
        this.lifing = lifingId;

    }

    public void deleteImage(char deleteYn) {
        this.deleteYn = deleteYn;
        this.deleteTime = new Timestamp(System.currentTimeMillis());
    }
}
