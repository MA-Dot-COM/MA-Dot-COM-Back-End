package com.sorhive.comprojectserver.room.command.domain.room;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

/**
 * <pre>
 * Class : RoomCreatorService
 * Comment: 방 생성자 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface RoomCreatorService {

    RoomCreator createRoomCreator(MemberCode roomCreatorMemberCode);
}
