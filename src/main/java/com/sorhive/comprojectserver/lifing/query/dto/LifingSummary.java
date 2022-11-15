package com.sorhive.comprojectserver.lifing.query.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingvisit.LifingVisit;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : LifingSummary
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-16       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class LifingSummary {
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

    private Long lifingId;

    private String lifingConetent;

    private Long lifingNo;

    private Long lifingCategoryNo;

    @JsonIgnore
    private Long analyzedLifingNo;

    private Long lifingWriterCode;

    private String lifingWriterId;

    private String lifingWriterName;

    private Timestamp uploadTime;

    private Character deleteYn;

    @JsonIgnore
    private List<LifingVisit> lifingVisitList = new ArrayList<LifingVisit>();

    @JsonIgnore
    private List<LifingCommentData> lifingCommentData = new ArrayList<>();

    private List<LifingImagePath> lifingImages = new ArrayList<>();

    protected LifingSummary() {}



}
