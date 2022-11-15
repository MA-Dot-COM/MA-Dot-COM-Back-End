package com.sorhive.comprojectserver.lifing.query.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

/**
 * <pre>
 * Class : LifingImageData
 * Comment: 라이핑 이미지 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-16       부시연           분석된 이미지 제거 && 변수명 변경
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class LifingImagePath {

    private Long lifingImageId;
    private String lifingImagePath;

    @JsonIgnore
    private String lifingId;

}