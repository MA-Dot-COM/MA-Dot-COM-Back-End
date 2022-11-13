package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.room.command.domain.room.RoomCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : RoomDetailResponseDto
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class RoomDetailResponseDto {

    private List<GuestBookData> guestBookDataList;
    private String id;
    private RoomCreator roomCreator;
    private List<Map<String,Object>> furnitures;
}
