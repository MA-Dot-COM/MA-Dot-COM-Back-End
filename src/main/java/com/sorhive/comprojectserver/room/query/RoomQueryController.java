package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.common.ResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : QueryRoomController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * 2022-11-10       부시연           방 조회 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class RoomQueryController {

    private static final Logger log = LoggerFactory.getLogger(RoomQueryController.class);
    private final RoomQueryService roomQueryService;

    /** 방 상세 조회 */
    @GetMapping("room/{roomId}")
    public ResponseEntity<ResponseDto> selectRoomDetail(@RequestHeader String Authorization, @PathVariable Long roomId) {

        log.info("[RoomQueryController] selectRoomDetail Start ===============");
        log.info("[RoomQueryController] roomId : " + roomId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "방 조회 성공", roomQueryService.selectRoomDetail(accessToken, roomId)));
    }
    
}
