package com.sorhive.comprojectserver.member.query.follow;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * Class : FollowMapper
 * Comment: 팔로우 매퍼
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-12-06       부시연           팔로워 검색 기능 추가
 * 2022-12-06       부시연           팔로잉 검색 기능 추가
 * 2022-12-07       부시연           팔로워 페이징 기능 추가
 * 2022-12-07       부시연           팔로잉 페이징 기능 추가
 * 2022-12-07       부시연           팔로워 목록 개수 추가
 * 2022-12-07       부시연           팔로잉 목록 개수 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Mapper
public interface FollowMapper {
    List<FollowData> findByFollowerId(Long memberCode);
    List<FollowData> findByFollowerId(Long memberCode, Long offset);
    List<FollowData> findByFollowingId(Long memberCode);
    List<FollowData> findByFollowingId(Long memberCode, Long offset);
    List<FollowData> searchFollowerById(String memberId, Long followingId);
    List<FollowData> searchFollowingById(String memberId, Long followerId);
    int countFollowing(Long memberCode);
    int countFollower(Long memberCode);
}
