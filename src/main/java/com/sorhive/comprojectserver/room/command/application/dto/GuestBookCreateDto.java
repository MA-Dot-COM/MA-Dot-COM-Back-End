package com.sorhive.comprojectserver.room.command.application.dto;

/**
 * <pre>
 * Class : GuestBookCreateDto
 * Comment: 클래스에 대한 간단 설명
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
public class GuestBookCreateDto {

    private String content;
    private String furnitureCategoryNumber;
    private Long furnitureNumber;

    private AngleDto angle;
    private BoxPositionDto boxPosition;
    private PositionDto position;
    private ScaleDto scale;

    public GuestBookCreateDto() {
    }

    public GuestBookCreateDto(String content, String furnitureCategoryNumber, Long furnitureNumber, AngleDto angle, BoxPositionDto boxPosition, PositionDto position, ScaleDto scale) {
        this.content = content;
        this.furnitureCategoryNumber = furnitureCategoryNumber;
        this.furnitureNumber = furnitureNumber;
        this.angle = angle;
        this.boxPosition = boxPosition;
        this.position = position;
        this.scale = scale;
    }

    public String getContent() {
        return content;
    }

    public String getFurnitureCategoryNumber() {
        return furnitureCategoryNumber;
    }

    public Long getFurnitureNumber() {
        return furnitureNumber;
    }

    public AngleDto getAngle() {
        return angle;
    }

    public BoxPositionDto getBoxPosition() {
        return boxPosition;
    }

    public PositionDto getPosition() {
        return position;
    }

    public ScaleDto getScale() {
        return scale;
    }
}