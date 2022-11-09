package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.room.command.domain.repository.MongoRoomRepository;
import com.sorhive.comprojectserver.room.command.domain.room.MongoRoom;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class QueryRoomService {
    private static final Logger log = LoggerFactory.getLogger(QueryRoomService.class);
    private RoomDataDao roomDataDao;
    private TokenProvider tokenProvider;

    private MongoRoomRepository mongoRoomRepository;


    public Optional<MongoRoom> selectRoomDetail(Long roomId) {
        log.info("[RoomService] createRoom Start ===================================");

        String roomNo = roomDataDao.findById(roomId).getRoomNo();

        return mongoRoomRepository.findById(roomNo);

    }

    public String selectRoomList(String accessToken) {

        Long.valueOf(tokenProvider.getUserCode(accessToken));

        return "";
    }
}
