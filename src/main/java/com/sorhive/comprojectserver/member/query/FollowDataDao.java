package com.sorhive.comprojectserver.member.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <pre>
 * Class : FollowDataDao
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface FollowDataDao extends JpaRepository<FollowData, Long> {

    List<FollowData> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
