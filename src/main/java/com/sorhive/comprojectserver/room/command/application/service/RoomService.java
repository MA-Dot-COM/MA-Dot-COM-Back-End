package com.sorhive.comprojectserver.room.command.application.service;

import com.sorhive.comprojectserver.config.file.S3MemberRoomFile;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.service.AuthService;
import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.repository.MemberRepository;
import com.sorhive.comprojectserver.room.command.application.dto.RoomCreateDto;
import com.sorhive.comprojectserver.room.command.domain.repository.MongoRoomRepository;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomRepository;
import com.sorhive.comprojectserver.room.command.domain.room.MongoRoom;
import com.sorhive.comprojectserver.room.command.domain.room.Room;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreator;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
 * 2022-11-10       부시연           접속용 방이미지 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MongoRoomRepository mongoRoomRepository;
    private final RoomRepository roomRepository;
    private final RoomCreatorService roomCreatorService;
    private final MemberRepository memberRepository;
    private TokenProvider tokenProvider;
    private S3MemberRoomFile s3MemberRoomFile;


    public RoomService(MongoRoomRepository mongoRoomRepository, RoomRepository roomRepository, RoomCreatorService roomCreatorService, MemberRepository memberRepository, TokenProvider tokenProvider, S3MemberRoomFile s3MemberRoomFile) {
        this.mongoRoomRepository = mongoRoomRepository;
        this.roomRepository = roomRepository;
        this.roomCreatorService = roomCreatorService;
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
        this.s3MemberRoomFile = s3MemberRoomFile;
    }

    @Transactional
    public String createRoom(String accessToken, RoomCreateDto roomCreateDto) {
        log.info("[RoomService] createRoom Start ===================================");
        log.info("[RoomService] roomCreateDto {}", roomCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        byte[] onlineRoomImage = roomCreateDto.getOnlineRoomImage();

        byte[] offlineRoomImage = roomCreateDto.getOfflineRoomImage();

        try {
            if(onlineRoomImage != null && offlineRoomImage != null) {

                Optional<Member> memberData = memberRepository.findByMemberCode(memberCode);
                memberData.get();
                Member member = memberData.get();

                member.setOfflineRoomImagePath(s3MemberRoomFile.upload(onlineRoomImage, "images", "offline_" + memberCode + ".png"));
                member.setOnlineRoomImagePath(s3MemberRoomFile.upload(offlineRoomImage, "images", "online_" + memberCode + ".png"));
                memberRepository.save(member);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RoomCreator roomCreator = roomCreatorService.createRoomCreator(new MemberCode(memberCode));

        MongoRoom mongoRoom = new MongoRoom(
                roomCreator,
                roomCreateDto.getFurnitures()
        );

        mongoRoomRepository.save(mongoRoom);

        Room newRoom = new Room(
                roomCreator.getMemberCode().getValue(),
                mongoRoom.getId(),
                roomCreator
        );

        roomRepository.save(newRoom);

        log.info("[RoomService] createRoom End ==============================");

        return mongoRoom.getId();

    }
}
