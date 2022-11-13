package com.sorhive.comprojectserver.lifing.query;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : LifingData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Entity
@Table(name = "tbl_lifing")
@Getter
public class LifingData {

    @Id
    @Column(name="lifing_id")
    private Long lifingId;

    @Column(name = "lifing_content")
    private String lifingConetent;

    @Column(name = "lifing_no")
    private Long lifingNo;

    @Column(name = "lifing_image_path")
    private String lifingImagePath;

    @Column(name = "lifing_writer_code")
    private Long memberCode;

    @Column(name = "lifing_writer_id")
    private Long memberId;

    @Column(name = "lifing_writer_name")
    private String name;

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_yn")
    private Character deleteYn;

    protected LifingData() {}

}
