package com.sorhive.comprojectserver.room.command.application.dto;

/**
 * <pre>
 * Class : PlacedFurnitureDto
 * Comment: 배치된 가구들에 대한 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-08       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class PlacedFurnitureDto {

    private String furnitureCategoryNumber;
    private Long furnitureNumber;

    public PlacedFurnitureDto() {
    }
    public String getFurnitureCategoryNumber() {
        return furnitureCategoryNumber;
    }

    public Long getFurnitureNumber() {
        return furnitureNumber;
    }
}
