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
public class QueryRoomController {

    private final QueryRoomService queryRoomService;

    @GetMapping("room/{roomId}")
    public ResponseEntity<ResponseDto> selectRoomDetail(@PathVariable Long roomId) {

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "방 조회 성공", queryRoomService.selectRoomDetail(roomId)));
    }

    @GetMapping("room/list")
    public ResponseEntity<ResponseDto> selectRoomList(@RequestHeader String Authorization) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "방 조회 성공", queryRoomService.selectRoomList(accessToken)));

    }
}
