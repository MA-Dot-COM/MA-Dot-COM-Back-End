package com.sorhive.comprojectserver.lifing.command.application.dto;

import lombok.Getter;

/**
 * <pre>
 * Class : LifingImageDto
 * Comment: 라이핑 이미지 요청 전송 객체
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
public class LifingImageDto {

    byte[] lifingImage;
    String lifingImageName;
    String lifingContent;

}
