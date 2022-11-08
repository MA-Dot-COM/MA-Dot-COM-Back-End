package com.sorhive.comprojectserver.room.command.application.dto;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.placedfurniture.PlacedFurniture;
import com.sorhive.comprojectserver.room.command.domain.room.Room;

import java.util.List;

/**
 * <pre>
 * Class : RoomCreateDto
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
public class RoomCreateDto {

    private String floorNumber;
    private String wallNumber;
    private Long memberCode;

    private GuestBookCreateDto guestBook;

    private List<PlacedFurnitureDto> furnitures;

    public RoomCreateDto() {
    }

    public RoomCreateDto(String floorNumber, String wallNumber, Long memberCode, GuestBookCreateDto guestBook, List<PlacedFurnitureDto> furnitures) {
        this.floorNumber = floorNumber;
        this.wallNumber = wallNumber;
        this.memberCode = memberCode;
        this.guestBook = guestBook;
        this.furnitures = furnitures;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public String getWallNumber() {
        return wallNumber;
    }

    public Long getMemberCode() {
        return memberCode;
    }

    public GuestBookCreateDto getGuestBook() {
        return guestBook;
    }

    public List<PlacedFurnitureDto> getFurnitures() {
        return furnitures;
    }
}