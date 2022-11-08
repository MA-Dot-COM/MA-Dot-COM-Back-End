package com.sorhive.comprojectserver.room.command.application.dto;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : RoomCreateDto
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class RoomCreateDto {
    private String floorNumber;
    private String wallNumber;
    private Long memberCode;
    private List<Map<String,Object>> furnitures;

    public RoomCreateDto() {
    }

    public RoomCreateDto(String floorNumber, String wallNumber, Long memberCode, List<Map<String, Object>> furnitures) {
        this.floorNumber = floorNumber;
        this.wallNumber = wallNumber;
        this.memberCode = memberCode;
        this.furnitures = furnitures;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public String getWallNumber() {
        return wallNumber;
    }

    public Long getMemberCode() {
        return memberCode;
    }

    public List<Map<String, Object>> getFurnitures() {
        return furnitures;
    }
}