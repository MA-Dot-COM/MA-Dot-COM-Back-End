package com.sorhive.comprojectserver.lifing.query.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingvisit.LifingVisit;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : LifingData
 * Comment: 라이핑 데이터 전송객체
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
@Table(name = "tbl_lifings")
@Getter
public class LifingData {

    @Id
    @Column(name="lifing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lifingId;

    @Column(name = "lifing_content")
    private String lifingConetent;

    @Column(name = "lifing_no")
    private Long lifingNo;

    @Column(name = "lifing_category_no")
    private Long lifingCategoryNo;


    @Column(name = "lifing_image_path")
    private String lifingImagePath;

    @Column(name = "lifing_writer_code")
    private Long lifingWriterCode;

    @Column(name = "lifing_writer_id")
    private String lifingWriterId;

    @Column(name = "lifing_writer_name")
    private String lifingWriterName;

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_yn")
    private Character deleteYn;

    @JsonIgnore
    @OneToMany(mappedBy = "lifing", cascade = CascadeType.ALL)
    private List<LifingVisit> lifingVisitList = new ArrayList<LifingVisit>();

    @JsonIgnore
    @OneToMany(mappedBy = "lifing", cascade = CascadeType.ALL)
    private List<LifingCommentData> lifingCommentData = new ArrayList<>();

    protected LifingData() {}

}
