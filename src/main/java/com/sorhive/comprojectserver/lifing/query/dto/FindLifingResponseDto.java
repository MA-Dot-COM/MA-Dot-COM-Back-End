package com.sorhive.comprojectserver.lifing.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Class : FindLifingResponseDto
 * Comment: 라이핑 상세 조회 반환 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-14       부시연           최초 생성
 * 2022-11-15       부시연           라이핑 댓글 목록 추가
 * 2022-11-16       부시연           라이핑 이미지 목록 추가
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

    private List<LifingImagePath> lifingImagePathList;

}
