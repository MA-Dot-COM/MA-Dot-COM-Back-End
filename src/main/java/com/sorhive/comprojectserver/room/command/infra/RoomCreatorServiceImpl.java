package com.sorhive.comprojectserver.room.command.infra;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreator;
import com.sorhive.comprojectserver.room.command.domain.room.RoomCreatorService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : RoomCreatorServiceImpl
 * Comment: 방 생성자 서비스 구현체
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
@Service
public class RoomCreatorServiceImpl implements RoomCreatorService {

    private MemberQueryService memberQueryService;

    public RoomCreatorServiceImpl(MemberQueryService memberQueryService) { this.memberQueryService = memberQueryService; }

    @Override
    public RoomCreator createRoomCreator(MemberCode roomCreatorMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(roomCreatorMemberCode.getValue());

        return new RoomCreator(MemberCode.of(memberData.getMemberCode()), memberData.getName(), memberData.getId());
    }
}
