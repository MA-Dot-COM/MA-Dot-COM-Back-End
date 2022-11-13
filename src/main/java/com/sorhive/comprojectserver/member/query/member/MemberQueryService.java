package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.application.NoMemberException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 * Class : MemberQueryService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * 2022-11-11       부시연           회원 목록 조회 추가
 * 2022-11-11       부시연           회원 검색 추가
 * 2022-11-12       부시연           회원 검색 수정
 * 2022-11-13       부시연           룸인 전용 자신을 포함한 랜덤 회원 7명 조회 기능 추가
 * 2022-11-13       부시연           자신을 제외한 회원 목록 조회 추가
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

    public Object findMemberByMemberId(String accessToken, String memberId) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MemberData> memberData = memberDataDao.findByIdLikeAndMemberCodeIsNot("%" + memberId + "%", memberCode);
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

    public Object findRandomMember(String accessToken) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MemberData> memberData = memberDataDao.findAllByMemberCodeIsNot(memberCode);

        if(memberData == null) {
            throw new NoMemberException();
        }

        FindRandomMemberResponseDto findRandomMemberResponseDto = new FindRandomMemberResponseDto();

        List<MemberData> memberDataList = new ArrayList<>();

        MemberData ownMemberData = memberDataDao.findByMemberCode(memberCode);

        memberDataList.add(ownMemberData);

        int count = 0;

        int maxMemberCode = memberMapper.findMaxMemberCode();

        while(true) {


                int memberSize[] = new int[maxMemberCode];

                Random randomNo = new Random();

                for (int i = 0; i < maxMemberCode; i++) {

                    memberSize[i] = randomNo.nextInt(maxMemberCode) + 1;

                    for (int j = 0; j < i; j++) {
                        if (memberSize[i] == memberSize[j]) {
                            i--;
                        }
                    }
                }

                int count2 = 0;

                for (int k = 0; k < maxMemberCode; k++) {

                    MemberData tempMemberData = memberDataDao.findByMemberCodeAndMemberCodeIsNot((long) memberSize[k], memberCode);

                    if (tempMemberData != null) {

                        memberDataList.add(tempMemberData);
                        count++;
                        if(count == 6 || count == maxMemberCode)  {
                            break;
                        }
                    }

                }

                break;

        }

        findRandomMemberResponseDto.setMemberDtoList(memberDataList);

        return findRandomMemberResponseDto;

    }

    public Object findAllMember(String accessToken) {

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MemberData> memberData = memberDataDao.findAllByMemberCodeIsNot(memberCode);

        if (memberData == null) {
            throw new NoMemberException();
        }

        return memberData;
    }
}
