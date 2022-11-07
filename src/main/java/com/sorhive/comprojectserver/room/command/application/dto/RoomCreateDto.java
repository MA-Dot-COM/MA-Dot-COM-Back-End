package com.sorhive.comprojectserver.room.command.application.dto;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

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

    private String roomInfo;

    private MemberCode roomCreatorMemberCode;

    public RoomCreateDto() {
    }

    public RoomCreateDto(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getRoomInfo() { return roomInfo; }

    public MemberCode getRoomCreatorMemberCode() { return roomCreatorMemberCode; }
}