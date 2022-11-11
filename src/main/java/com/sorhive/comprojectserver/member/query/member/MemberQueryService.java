package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.NoMemberException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private MemberMapper memberMapper;

    private final TokenProvider tokenProvider;

    public MemberQueryService(MemberDataDao memberDataDao, MemberMapper memberMapper, TokenProvider tokenProvider) {
        this.memberDataDao = memberDataDao;
        this.memberMapper = memberMapper;
        this.tokenProvider = tokenProvider;
    }

    public MemberData getMemberData(String memberId) {

        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new NoMemberException();
        }

        return memberData;
    }

    public MemberData getMemberData(Long memberCode) {
        MemberData memberData = memberDataDao.findByMemberCode(memberCode);
        if(memberData == null) {
            throw new NoMemberException();
        }

        return memberData;

    }

    public Object findMemberByMemberId(String memberId) {

        List<MemberData> memberData = memberDataDao.findByIdLike("%" + memberId + "%");
        if(memberData == null) {
            throw new NoMemberException();
        }

        return  memberData;

    }

    public Object findAllByMemberCode(String accessToken, Long offset) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MemberSummary> memberSummary = memberMapper.findAllByMemberCode(memberCode, offset * 30);

        if (memberSummary == null) {
            throw new NoMemberException();
        }

        return memberSummary;
    }
}
