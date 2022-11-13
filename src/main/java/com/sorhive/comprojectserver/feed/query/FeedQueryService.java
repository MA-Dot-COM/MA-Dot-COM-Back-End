package com.sorhive.comprojectserver.feed.query;

import com.sorhive.comprojectserver.feed.command.application.exception.NoFeedException;
import com.sorhive.comprojectserver.feed.query.dto.FeedCommentSummary;
import com.sorhive.comprojectserver.feed.query.dto.FeedImageSummary;
import com.sorhive.comprojectserver.feed.query.dto.FeedRequestDto;
import com.sorhive.comprojectserver.feed.query.dto.FeedResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : FeedQueryService
 * Comment: 피드 조회용 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
@AllArgsConstructor
public class FeedQueryService {

    private FeedMapper feedMapper;
    private static final Logger log = LoggerFactory.getLogger(FeedQueryService.class);

    /* 피드 전체 조회 */
    public Object selectAllFeed(FeedRequestDto feedRequestDto) {

        log.info("[FeedQueryService] selectAllFeed Start ===============");
        log.info("[FeedQueryService] feedRequestDto : " + feedRequestDto);

        Long memberCode = feedRequestDto.getMemberCode();
        int pageNo = feedRequestDto.getPageNo() - 1;

        /* 피드가 없으면 예외처리 */
        if(feedMapper.selectAllFeed(memberCode, pageNo) == null) {
            throw new NoFeedException();
        }

        return feedMapper.selectAllFeed(memberCode, pageNo);
    }

    /* 피드 상세 조회 */
    public Object selectFeedDetail(Long feedId) {

        log.info("[FeedQueryService] selectFeedDetail Start ===============");
        log.info("[FeedQueryService] feedId : " + feedId);

        FeedResponseDto feedResponseDto = new FeedResponseDto();

        /* 피드 없을 경우 예외 처리 */
        if(feedMapper.selectFeedByFeedId(feedId) == null) {
            throw new NoFeedException();
        }

        feedResponseDto.setFeedSummary(feedMapper.selectFeedByFeedId(feedId));

        /* 피드에 이미지 있는지 확인 */
        if(feedMapper.selectAllFeedImages(feedId) != null) {
            
            List<FeedImageSummary> feedImageSummaryList = feedMapper.selectAllFeedImages(feedId);

            feedResponseDto.setFeedImageSummaryList(feedImageSummaryList);
        }

        /* 피드에 댓글 있는지 확인 */
        if(feedMapper.selectAllFeedComments(feedId) != null) {

            List<FeedCommentSummary> feedCommentSummaries = feedMapper.selectAllFeedComments(feedId);

            feedResponseDto.setFeedSummaryList(feedCommentSummaries);
        }


        return feedResponseDto;
    }
}
