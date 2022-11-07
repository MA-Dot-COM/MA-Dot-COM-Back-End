package com.sorhive.comprojectserver.room.command.application.service;

import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import com.sorhive.comprojectserver.room.command.application.dto.RoomCreateDto;
import com.sorhive.comprojectserver.room.command.domain.room.Room;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreator;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomRepository;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class : RoomService
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
@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final RoomRepository roomRepository;

    private final RoomCreatorService roomCreatorService;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomCreatorService roomCreatorService) {
        this.roomRepository = roomRepository;
        this.roomCreatorService = roomCreatorService;
    }

    @Transactional
    public Long createRoom(RoomCreateDto roomCreateDto) {
        log.info("[RoomService] createRoom Start ===================================");
        log.info("[RoomService] roomCreateDto {}", roomCreateDto);

        RoomCreator roomCreator = roomCreatorService.createRoomCreator(roomCreateDto.getRoomCreatorMemberCode());

        Room room = new Room(
                roomCreator
        );

        roomRepository.save(room);

        log.info("[RoomService] createRoom End ==============================");

        return room.getId();

    }
}
