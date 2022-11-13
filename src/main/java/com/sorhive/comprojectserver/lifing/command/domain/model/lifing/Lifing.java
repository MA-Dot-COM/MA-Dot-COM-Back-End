package com.sorhive.comprojectserver.lifing.command.domain.model.lifing;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Lifing
 * Comment: 라이핑 도메인 모델
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
@Table(name = "tbl_lifing")
@Getter
public class Lifing {

    @Id
    @Column(name="lifing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lifingId;

    @Column(name = "lifing_content")
    private String lifingConetent;

    @Column(name = "lifing_no")
    private Long lifingNo;

    @Column(name = "lifing_image_path")
    private String lifingImagePath;

    private LifingWriter lifingWriter;

    @Column(name = "lifing_create_time")
    private Timestamp createTime;

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_time")
    private Timestamp deleteTime;

    @Column(name = "lifing_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    protected Lifing() { }

    public Lifing(LifingWriter lifingWriter, Long lifingNo, String lifingConetent, String lifingImagePath) {
        this.lifingWriter = lifingWriter;
        this.lifingNo = lifingNo;
        this.lifingConetent = lifingConetent;
        this.lifingImagePath = lifingImagePath;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
        this.deleteYn = 'N';
    }
}
