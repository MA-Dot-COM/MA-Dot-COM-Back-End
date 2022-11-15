package com.sorhive.comprojectserver.lifing.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Class : FindLifingResponseDto
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-14       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class FindLifingResponseDto {

    private LifingData lifingData;

    private List<LifingCommentData> lifingCommentDataList;

}
