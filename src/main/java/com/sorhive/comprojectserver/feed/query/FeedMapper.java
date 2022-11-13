package com.sorhive.comprojectserver.feed.query;

import com.sorhive.comprojectserver.feed.query.dto.FeedCommentSummary;
import com.sorhive.comprojectserver.feed.query.dto.FeedImageSummary;
import com.sorhive.comprojectserver.feed.query.dto.FeedSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * Class : FeedMapper
 * Comment: 피드 조회용 매퍼
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
@Mapper
public interface FeedMapper {
    List<FeedSummary> selectAllFeed(Long memberCode, int pageNo);

    List<FeedImageSummary> selectAllFeedImages(Long feedId);

    List<FeedCommentSummary> selectAllFeedComments(Long feedId);

    FeedSummary selectFeedByFeedId(Long feedId);
}
