package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.member.query.member.MemberData;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <pre>
 * Class : GuestBookDataDao
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
public interface GuestBookDataDao extends Repository<GuestBookData, Long> {

    List<GuestBookData> findByMemberCodeAndRoomIdAndDeleteYnEquals(Long memberCode, Long roomId, char n);

}
