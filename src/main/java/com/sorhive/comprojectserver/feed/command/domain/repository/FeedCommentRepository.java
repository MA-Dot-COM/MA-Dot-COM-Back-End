package com.sorhive.comprojectserver.feed.command.domain.repository;

import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedComment;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : FeedCommentRepository
 * Comment: 피드 댓글 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface FeedCommentRepository extends Repository<FeedComment, Long> {

    void save(FeedComment feedComment);
}
