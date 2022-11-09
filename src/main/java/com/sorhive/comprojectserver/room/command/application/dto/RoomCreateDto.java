package com.sorhive.comprojectserver.room.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

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
 * 2022-11-10       부시연           접속용 방 이미지 대응
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@AllArgsConstructor
@Getter
public class RoomCreateDto {

    private MultipartFile onlineRoomImage;
    private MultipartFile offlineRoomImage;
    private List<Map<String,Object>> furnitures;
    
}