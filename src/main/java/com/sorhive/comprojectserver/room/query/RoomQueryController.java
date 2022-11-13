package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.common.ResponseDto;
import lombok.AllArgsConstructor;
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

    private final RoomQueryService roomQueryService;

    @GetMapping("room/{roomId}")
    public ResponseEntity<ResponseDto> selectRoomDetail(@RequestHeader String Authorization, @PathVariable Long roomId) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "방 조회 성공", roomQueryService.selectRoomDetail(accessToken, roomId)));
    }

}
