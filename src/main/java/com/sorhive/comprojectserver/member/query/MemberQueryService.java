package com.sorhive.comprojectserver.member.query;

import com.sorhive.comprojectserver.member.query.MemberDataDao;
import com.sorhive.comprojectserver.member.query.MemberData;
import com.sorhive.comprojectserver.member.command.application.NoMemberException;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : IdPasswordNotMatchingException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class MemberQueryService {

    private MemberDataDao memberDataDao;

    public MemberQueryService(MemberDataDao memberDataDao) {
        this.memberDataDao = memberDataDao;
    }

    public MemberData getMemberData(String memberId) {

        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new NoMemberException();
        }

        return memberData;
    }
}
