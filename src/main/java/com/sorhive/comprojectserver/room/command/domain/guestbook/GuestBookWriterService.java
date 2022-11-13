package com.sorhive.comprojectserver.room.command.domain.guestbook;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

/**
 * <pre>
 * Class : GuestBookWriterService
 * Comment: 방명록 작성자 서비스
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
public interface GuestBookWriterService {

    GuestBookWriter createGuestBookWriter(MemberCode guestbookWriterMemberCode);
}
