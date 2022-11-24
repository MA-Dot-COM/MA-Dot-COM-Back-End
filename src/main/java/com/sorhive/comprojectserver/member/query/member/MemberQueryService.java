package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.member.command.exception.NoMemberException;
import com.sorhive.comprojectserver.member.query.avatar.AvatarData;
import com.sorhive.comprojectserver.member.query.avatar.AvatarDataDao;
import com.sorhive.comprojectserver.member.query.follow.FollowSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 * Class : MemberQueryService
 * Comment: 멤버 조회용 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * 2022-11-11       부시연           회원 목록 조회 추가
 * 2022-11-11       부시연           회원 검색 추가
 * 2022-11-12       부시연           회원 검색 수정
 * 2022-11-13       부시연           벌집타기 전용 자신을 포함한 랜덤 회원 7명 조회 기능 추가
 * 2022-11-13       부시연           자신을 제외한 회원 목록 조회 추가
 * 2022-11-13       부시연           자신을 포함한 회원 목록 조회 추가
 * 2022-11-13       부시연           룸인 전용 팔로잉 회원 목록 + 랜덤 회원 총 7명 조회 추가
 * 2022-11-14       부시연           마이페이지 정보 조회 기능 추가
 * 2022-11-14       부시연           회원 상세 조회 기능 추가
 * 2022-11-20       부시연           회원 상세 조회 기능 수정
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class MemberQueryService {

    private static final Logger log = LoggerFactory.getLogger(MemberQueryService.class);
    private MemberDataDao memberDataDao;
    private MemberMapper memberMapper;
    private AvatarDataDao avatarDataDao;
    private final TokenProvider tokenProvider;

    public MemberQueryService(MemberDataDao memberDataDao, MemberMapper memberMapper, AvatarDataDao avatarDataDao, TokenProvider tokenProvider) {
        this.memberDataDao = memberDataDao;
        this.memberMapper = memberMapper;
        this.avatarDataDao = avatarDataDao;
        this.tokenProvider = tokenProvider;
    }
    
    /** 회원 아이디로 회원검색 */
    public MemberData getMemberData(String memberId) {

        log.info("[MemberQueryService] getMemberData Start ================");
        log.info("[MemberQueryService] memberId : " + memberId);

        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new NoMemberException();
        }

        return memberData;
    }

    public MemberData getMemberData(Long memberCode) {

        log.info("[MemberQueryService] getMemberData Start ================");
        log.info("[MemberQueryService] memberCode : " + memberCode);

        MemberData memberData = memberDataDao.findByMemberCode(memberCode);
        if(memberData == null) {
            throw new NoMemberException();
        }

        return memberData;

    }

    /** 회원 ID 검색 */
    public List<MemberData> findMemberByMemberId(String accessToken, String memberId) {

        log.info("[MemberQueryService] findMemberByMemberId Start ================");
        log.info("[MemberQueryService] memberId : " + memberId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MemberData> memberData = memberDataDao.findByIdLikeAndMemberCodeIsNot("%" + memberId + "%", memberCode);
        if(memberData == null) {
            throw new NoMemberException();
        }

        return memberData;

    }

    /** 멤버코드로 팔로우 하고 있는 회원 조회 */
    public FindAllMemberResponseDto findAllByMemberCode(String accessToken, Long offset) {

        log.info("[MemberQueryService] findAllByMemberCode Start ================");
        log.info("[MemberQueryService] offset : " + offset);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 자신 조회하기 */
        MemberSummary memberSummary = memberMapper.findOneByMemberCode(memberCode);

        List<MemberSummary> memberSummaryList = new ArrayList<>();

        /* 응답 전송 객체 만들기 */
        FindAllMemberResponseDto findAllMemberResponseDto = new FindAllMemberResponseDto();

        if (memberSummary == null) {
            throw new NoMemberException("해당 회원은 존재 하지 않습니다.");
        }

        /* 응답 전송 객체에 담기 위해 리스트에 자신의 정보 넣기 */
        memberSummaryList.add(memberSummary);

        /* 자신이 팔로우 하고 있는 인원들의 목록 */
        List<MemberSummary> tempMemberSummaryList = memberMapper.findAllFollowerByMemberCode(memberCode, offset * 30);

        if(!tempMemberSummaryList.isEmpty()) {

            for (int i = 0; i < tempMemberSummaryList.size(); i++) {

                /* 응답 전송 객체에 담기 위해 리스트에 팔로우 하고 있는 인원들 정보 넣기 */
                memberSummaryList.add(tempMemberSummaryList.get(i));

            }

        }

        findAllMemberResponseDto.setMemberSummary(memberSummaryList);

        return findAllMemberResponseDto;
    }

    /** 랜덤 회원 조회 */
    public FindRandomMemberResponseDto findRandomMember(String accessToken) {

        log.info("[MemberQueryService] findRandomMember Start ================");

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

        /* 자신의 아바타 정보 불러오기 */
        if(avatarDataDao.findByMemberCode(memberCode) != null) {

            AvatarData ownAvatarData = avatarDataDao.findByMemberCode(memberCode);
            findRandomMemberResponseDto.setOwnAvatarData(ownAvatarData);

        }

        findRandomMemberResponseDto.setMemberDtoList(memberDataList);

        return findRandomMemberResponseDto;

    }

    /** 룸인 회원 조회 */
    public FindRoomInMemberResponseDto findRoomInMember(String accessToken, Long roomId) {

        log.info("[MemberQueryService] findRoomInMember Start ================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        FindRoomInMemberResponseDto findRoomInMemberResponseDto = new FindRoomInMemberResponseDto();

        List<MemberData> memberDataList = new ArrayList<>();

        /* 회원번호로 조회하기 */
        MemberData followerMemberData = memberDataDao.findByMemberCode(roomId);

        if(followerMemberData == null) {
            throw new NoMemberException("회원 정보가 없습니다");
        }
        
        memberDataList.add(followerMemberData);

        /* 팔로워의 팔로잉 조회 */
        List<MemberData> followerMemberDataList = memberMapper.findAllFollowerByFollowerCode(roomId);

        int followerCount = 0;
        followerCount = followerMemberDataList.size();

        /* 팔로워의 팔로잉 목록이 있다면 하나씩 꺼내서 memberDataList에 넣어준다. */
        if(followerMemberDataList != null ){

            for (int i = 0; i < followerMemberDataList.size(); i++) {

                memberDataList.add(followerMemberDataList.get(i));

            }
        }

        int count = 0;

        int maxMemberCode = memberMapper.findMaxMemberCode();

        /* 무한루프 */
        while(true) {

            /* 멤버코드의 최대값만큼 회원이 존재한다고 가정한다. */
            int memberSize[] = new int[maxMemberCode];

            /* 랜덤 난수값을 만든다. */
            Random randomNo = new Random();

            for (int i = 0; i < maxMemberCode; i++) {

                /* 랜덤 정수값을 만들어서 부여한다. */
                memberSize[i] = randomNo.nextInt(maxMemberCode) + 1;

                for (int j = 0; j < i; j++) {

                    /* 만약 중복이 발생한다면 다시 조정을 한다. */
                    if (memberSize[i] == memberSize[j]) {
                        i--;
                    }
                }
            }

            /* 멤버코드의 최대값만큼 반복을 시킨다. */
            for (int k = 0; k < maxMemberCode; k++) {

                /* 해당 멤버코드를 제외한 회원들을 조회한다. */
                MemberData tempMemberData = memberDataDao.findByMemberCodeAndMemberCodeIsNot((long) memberSize[k], memberCode);

                if (tempMemberData != null) {

                    memberDataList.add(tempMemberData);
                    count++;

                    /* 6명이 되거나 최대회원수가 6명도 채 안되면 그 회원 수만큼만 조회하고 무한루프를 탈출한다. */
                    if(count == (6 - followerCount) || count == maxMemberCode)  {
                        break;
                    }
                }

            }

            break;

        }
        
        /* 자신의 아바타 정보 불러오기 */
        if(avatarDataDao.findByMemberCode(memberCode) != null) {

            AvatarData ownAvatarData = avatarDataDao.findByMemberCode(memberCode);
            findRoomInMemberResponseDto.setOwnAvatarData(ownAvatarData);

        }

        /* 조회했던 회원 정보를 전송객체에 담고 반환한다. */
        findRoomInMemberResponseDto.setMemberDtoList(memberDataList);

        return findRoomInMemberResponseDto;

    }
    
    /** 모든 회원 조회 */
    public List<MemberData> findAllMember(String accessToken) {

        log.info("[MemberQueryService] findAllMember Start ================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 자신을 제외한 모든 회원 조회하기 */
        List<MemberData> memberData = memberDataDao.findAllByMemberCodeIsNot(memberCode);

        if (memberData == null) {
            throw new NoMemberException();
        }

        return memberData;
    }

    /** 마이페이지 정보 조회 */
    public MypageDto selectMypage(String accessToken) {

        log.info("[MemberQueryService] selectMypage Start ================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 자기 자신 요약 정보 불러오기 */
        MemberSummary memberSummary = memberMapper.findOneByMemberCode(memberCode);

        /* 회원 상세 정보 응답 전송 객체 만들고 값 넣어주기 */
        MypageDto mypageDto = new MypageDto(
                memberSummary.getMemberId(),
                memberSummary.getMemberCode(),
                memberSummary.getMemberName(),
                memberSummary.getRoomImage(),
                memberMapper.countFeed(memberCode),
                memberMapper.countFollower(memberCode),
                memberMapper.countFollowing(memberCode)
        );
        return mypageDto;
    }


    /** 회원 상세 조회 */
    public Object selectMemberByMemberCode(String accessToken,Long memberCode) {

        log.info("[MemberQueryService] selectMypage Start ================");

        Long ownMemberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(memberDataDao.findByMemberCode(memberCode) == null) {
            log.warn("[selectMemberByMemberCode]");
            log.warn("[NoMemberException]");
            throw new NoMemberException("해당하는 회원이 없습니다.");
        }

        if(memberMapper.findOneByMemberCode(memberCode) == null) {

            if(memberMapper.findByMemberCode(memberCode) != null) {

                MemberData memberSummary = memberDataDao.findFirstByMemberCode(memberCode);

                MypageDto mypageDto = new MypageDto(
                        memberSummary.getId(),
                        memberSummary.getMemberCode(),
                        memberSummary.getName(),
                        memberSummary.getMemberRoomImage(),
                        memberMapper.countFeed(memberCode),
                        memberMapper.countFollower(memberCode),
                        memberMapper.countFollowing(memberCode)
                );

                return mypageDto;
            }

        }

        /* 회원 상세 정보 불러오기 */
        MemberSummary memberSummary = memberMapper.findOneByMemberCode(memberCode);

        /* 회원 상세 정보 응답 전송 객체 만들고 값 넣어주기 */
        MypageDto mypageDto = new MypageDto(
                memberSummary.getMemberId(),
                memberSummary.getMemberCode(),
                memberSummary.getMemberName(),
                memberSummary.getRoomImage(),
                memberMapper.countFeed(memberCode),
                memberMapper.countFollower(memberCode),
                memberMapper.countFollowing(memberCode)
        );

        if(memberMapper.findByMemberCodeAndOwnMemberCode(memberCode, ownMemberCode) != null){
            FollowSummary followSummary = memberMapper.findByMemberCodeAndOwnMemberCode(memberCode, ownMemberCode);
            mypageDto.addFollowSummary(followSummary);
        } else {
            FollowSummary followSummary = new FollowSummary(-1L,-1L,-1L);
            mypageDto.addFollowSummary(followSummary);
        }

        return mypageDto;
    }
}
