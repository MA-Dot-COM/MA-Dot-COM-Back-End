package com.sorhive.comprojectserver.room.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.room.command.application.dto.RoomCreateDto;
import com.sorhive.comprojectserver.room.command.application.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * Class : RoomController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * 2022-11-09       부시연           방 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("api/v1")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "room", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDto> createRoom(@RequestHeader String Authorization,
                                                  @RequestPart(name="furnitures") RoomCreateDto roomCreateDto,
                                                  @RequestPart(name="onlineRoomImage") MultipartFile onlineRoomImage,
                                                  @RequestPart(name = "offlineRoomImage") MultipartFile offlineRoomImage) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "방 생성 성공", roomService.createRoom(accessToken, roomCreateDto, onlineRoomImage, offlineRoomImage)));
    }
}
