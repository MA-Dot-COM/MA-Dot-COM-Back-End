package com.sorhive.comprojectserver.room.command.infra;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBookWriter;
import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBookWriterService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : GuestBookWriterServiceImpl
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
@Service
public class GuestBookWriterServiceImpl implements GuestBookWriterService {

    private MemberQueryService memberQueryService;

    public GuestBookWriterServiceImpl(MemberQueryService memberQueryService) { this.memberQueryService = memberQueryService; }

    @Override
    public GuestBookWriter createGuestBookWriter(MemberCode guestbookWriterMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(guestbookWriterMemberCode.getValue());

        return new GuestBookWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName());
    }
}