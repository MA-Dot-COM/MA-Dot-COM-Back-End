package com.sorhive.comprojectserver.room.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.room.command.application.dto.RoomCreateDto;
import com.sorhive.comprojectserver.room.command.domain.repository.MongoRoomRepository;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomRepository;
import com.sorhive.comprojectserver.room.command.domain.room.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <pre>
 * Class : RoomService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * 2022-11-09       부시연           방 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
@AllArgsConstructor
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MongoRoomRepository mongoRoomRepository;
    private final RoomRepository roomRepository;
    private final RoomCreatorService roomCreatorService;
    private TokenProvider tokenProvider;

    @Transactional
    public String createRoom(String accessToken, RoomCreateDto roomCreateDto) {
        log.info("[RoomService] createRoom Start ===================================");
        log.info("[RoomService] roomCreateDto {}", roomCreateDto);

        RoomCreator roomCreator = roomCreatorService.createRoomCreator(new MemberCode(Long.valueOf(tokenProvider.getUserCode(accessToken))));

        Optional<Room> room = roomRepository.findById(roomCreator.getMemberCode().getValue());

        if(room.isEmpty()) {

            Room newRoom = new Room(
                    roomCreator.getMemberCode().getValue(),
                    roomCreator
            );

            roomRepository.save(newRoom);
        }

        MongoRoom mongoRoom = new MongoRoom(
                roomCreator,
                roomCreateDto.getFloorNumber(),
                roomCreateDto.getWallNumber(),
                roomCreateDto.getFurnitures()
        );

        mongoRoomRepository.save(mongoRoom);

        log.info("[RoomService] createRoom End ==============================");

        return mongoRoom.getId();

    }
}
