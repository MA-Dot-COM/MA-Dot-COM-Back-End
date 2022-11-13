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
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Mapper
public interface FollowMapper {
    List<FollowData> findByFollowerId(Long memberCode);
    List<FollowData> findByFollowingId(Long memberCode);
}
