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
    private List<Map<String,Object>> furnitures;

    public RoomCreateDto() {
    }

    public RoomCreateDto(List<Map<String, Object>> furnitures) {
        this.furnitures = furnitures;
    }
    public List<Map<String, Object>> getFurnitures() {
        return furnitures;
    }
}