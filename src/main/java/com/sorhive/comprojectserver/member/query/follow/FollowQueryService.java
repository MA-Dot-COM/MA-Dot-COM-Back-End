package com.sorhive.comprojectserver.member.query.follow;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public FollowerListResponseDto findFollowerList(Long memberCode) {

        log.info("[FollowQueryService] findFollowerList Start ================");

        List<FollowData> followData = followMapper.findByFollowerId(memberCode);

        FollowerListResponseDto followerListResponseDto = new FollowerListResponseDto(
                followData,
                followData.size()
        );

        log.info("[FollowQueryService] findFollowerList End ================");

        return followerListResponseDto;
    }

    /** 팔로잉 목록 조회하기 */
    public FollowingListResponseDto findFollowingList(Long memberCode) {

        log.info("[FollowQueryService] findFollowingList Start ================");

        List<FollowData> followData = followMapper.findByFollowingId(memberCode);

        FollowingListResponseDto followingListResponseDto = new FollowingListResponseDto(
                followData,
                followData.size()
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
