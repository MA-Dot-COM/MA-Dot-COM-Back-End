package com.sorhive.comprojectserver.member.query.follow;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : FollowQueryService
 * Comment: 팔로우 조회 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * 2022-11-20       부시연           팔로우 목록 조회 수정
 * 2022-11-20       부시연           팔로잉 목록 조회 수정
 * 2022-12-06       부시연           팔로워 검색
 * 2022-12-06       부시연           팔로잉 검색
 * 2022-12-07       부시연           팔로우 목록 페이징 기능 추가
 * 2022-12-07       부시연           팔로잉 목록 페이징 기능 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class FollowQueryService {

    private static final Logger log = LoggerFactory.getLogger(FollowQueryController.class);
    private final FollowMapper followMapper;
    private final TokenProvider tokenProvider;

    public FollowQueryService(FollowMapper followMapper, TokenProvider tokenProvider) {
        this.followMapper = followMapper;
        this.tokenProvider = tokenProvider;
    }

    /** 팔로워 목록 조회하기 */
    public FollowerListResponseDto findFollowerList(Long memberCode, Optional<FollowerRequestDto> followerRequestDto) {

        log.info("[FollowQueryService] findFollowerList Start ================");

        /* 팔로우데이터 초기화 */
        List<FollowData> followData = null;

        /* 전송객체가 비어있지 않다면 */
        if(!followerRequestDto.isEmpty()) {
            Long offset = followerRequestDto.get().getPage() * 15;
            followData = followMapper.findByFollowerId(memberCode, offset);
        } else {
            followData = followMapper.findByFollowerId(memberCode);
        }

        FollowerListResponseDto followerListResponseDto = new FollowerListResponseDto(
                followData,
                followMapper.countFollower(memberCode)
        );

        log.info("[FollowQueryService] findFollowerList End ================");

        return followerListResponseDto;
    }

    /** 팔로잉 목록 조회하기 */
    public FollowingListResponseDto findFollowingList(Long memberCode, Optional<FollowingRequestDto> followingRequestDto) {

        log.info("[FollowQueryService] findFollowingList Start ================");

        /* 팔로우데이터 초기화 */
        List<FollowData> followData = null;

        /* 전송객체가 비어있지 않다면 */
        if(!followingRequestDto.isEmpty()) {
            Long offset = followingRequestDto.get().getPage() * 15;
            followData = followMapper.findByFollowingId(memberCode, offset);
        } else {
            followData = followMapper.findByFollowingId(memberCode);
        }


        FollowingListResponseDto followingListResponseDto = new FollowingListResponseDto(
                followData,
                followMapper.countFollowing(memberCode)
        );

        log.info("[FollowQueryService] findFollowerList End ================");

        return followingListResponseDto;
    }

    /** 팔로워 검색 */
    public FindFollowerByIdResponseDto searchFollowerById(String memberId, String accessToken) {

        log.info("[FollowQueryService] searchFollowerById Start ================");

        Long followingId = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<FollowData> followData = followMapper.searchFollowerById(memberId, followingId);

        FindFollowerByIdResponseDto findFollowerByIdResponseDto = new FindFollowerByIdResponseDto(
                followData
        );

        log.info("[FollowQueryService] searchFollowerById End ================");

        return findFollowerByIdResponseDto;

    }

    /** 팔로잉 검색 */
    public FindFollowingByIdResponseDto searchFollowingById(String memberId, String accessToken) {

        log.info("[FollowQueryService] searchFollowingById Start ================");

        Long followerId = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<FollowData> followData = followMapper.searchFollowingById(memberId, followerId);

        FindFollowingByIdResponseDto findFollowingByIdResponseDto = new FindFollowingByIdResponseDto(
                followData
        );

        log.info("[FollowQueryService] searchFollowingById End ================");

        return findFollowingByIdResponseDto;
    }
}
