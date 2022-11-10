package com.sorhive.comprojectserver.member.command.domain.repository;

import com.sorhive.comprojectserver.member.command.domain.model.follow.Follow;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowerId;
import com.sorhive.comprojectserver.member.command.domain.model.follow.FollowingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * <pre>
 * Class : FollowRepository
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
 * @see (참고할 class 또는 외부 url)
 */
public interface FollowRepository extends Repository<Follow, Long> {

    void save(Follow follow);

    Optional<Follow> findByFollowIdAndDeleteYnEquals(Long id, Character n);


    @Query(value = "select follow " +
            "from Follow follow " +
            "where follow.deleteYn = 'N' " +
            "and follow.followingId = :followingId " +
            "and follow.followerId = :followerId")
    Optional<Follow> findByFollowerId(@Param("followerId")FollowerId followerId, @Param("followingId")FollowingId followingId);
}
