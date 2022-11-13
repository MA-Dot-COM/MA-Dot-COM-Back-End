package com.sorhive.comprojectserver.feed.command.domain.repository;

import com.sorhive.comprojectserver.feed.command.domain.model.feed.Feed;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : FeedRepository
 * Comment: 피드 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface FeedRepository extends Repository<Feed, Long> {

    Feed findByFeedId(Long id);

    void save(Feed feed);

    Optional<Feed> findByFeedIdAndDeleteYnEquals(Long feedId, char n);
}
