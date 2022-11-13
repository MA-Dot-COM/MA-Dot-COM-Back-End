package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.room.command.domain.repository.MongoRoomRepository;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomRepository;
import com.sorhive.comprojectserver.room.command.domain.repository.RoomVisitRepository;
import com.sorhive.comprojectserver.room.command.domain.room.MongoRoom;
import com.sorhive.comprojectserver.room.command.domain.room.Room;
import com.sorhive.comprojectserver.room.command.domain.room.RoomId;
import com.sorhive.comprojectserver.room.command.domain.roomvisit.RoomVisit;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : QueryRoomService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * 2022-11-10       부시연           룸 상세 조회 추가
 * 2022-11-13       부시연           룸 조회수 기능 추가
 * 2022-11-13       부시연           방명록 조회 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class RoomQueryService {
    private static final Logger log = LoggerFactory.getLogger(RoomQueryService.class);
    private RoomDataDao roomDataDao;
    private RoomRepository roomRepository;
    private RoomVisitRepository roomVisitRepository;
    private MongoRoomRepository mongoRoomRepository;
    private GuestBookDataDao guestBookDataDao;
    private TokenProvider tokenProvider;


    /* 방 상세 조회*/
    @Transactional
    public RoomDetailResponseDto selectRoomDetail(String accessToken, Long roomId) {
        log.info("[RoomService] selectRoomDetail Start ===================================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        Room room = roomRepository.findById(roomId);

        if(room == null) {
            throw new NoRoomException("룸 데이터가 존재하지 않습니다.");
        }

        /* 방문하려는 사람의 방이 방 주인이 아닐 경우 */
        if(roomId != memberCode) {

            /* 방의 조회수 증가 */
            room.setRoomCount(1L);
            roomRepository.save(room);

            /* 방문 기록 저장 */
            RoomVisit roomVisit = new RoomVisit(
                    new MemberCode(memberCode),
                    new RoomId(roomId)
            );

            roomVisitRepository.save(roomVisit);
        }

        /* 방 상세 조회 응답 객체 생성하기 */
        RoomDetailResponseDto roomDetailResponseDto = new RoomDetailResponseDto();

        /* 만약 방에 삭제가 안된 방명록이 있다면 */
        if(guestBookDataDao.findByRoomIdAndDeleteYnEquals(roomId, 'N') != null) {

            /* 방명록 리스트 가져오기 */
            List<GuestBookData> guestBookData =  guestBookDataDao.findByRoomIdAndDeleteYnEquals(roomId, 'N');

            /* 응답 객체에 방명록 리스트 담기 */
            roomDetailResponseDto.setGuestBookDataList(guestBookData);

        }

        /* 룸 데이터 조회하기 */
        RoomData roomData = roomDataDao.findById(roomId);

        String roomNo = roomData.getRoomNo();
        roomDetailResponseDto.setRoomId(roomData.getId());

        /* 몽고 DB에 있는 해당 룸 데이터 조회하기 */
        Optional<MongoRoom> mongoRooms = mongoRoomRepository.findById(roomNo);

        /* 응답 객체에 방 정보 담기 */
        roomDetailResponseDto.setRoomCreator(mongoRooms.get().getRoomCreator());
        roomDetailResponseDto.setFurnitures(mongoRooms.get().getFurnitures());
        roomDetailResponseDto.setMongoRoomid(mongoRooms.get().getId());

        return roomDetailResponseDto;

    }

}
