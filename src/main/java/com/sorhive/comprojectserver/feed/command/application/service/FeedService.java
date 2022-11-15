package com.sorhive.comprojectserver.feed.command.application.service;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCommentCreateDto;
import com.sorhive.comprojectserver.feed.command.application.dto.ResponseFeedCommentDto;
import com.sorhive.comprojectserver.feed.command.application.exception.NoFeedException;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.Feed;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedComment;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriterService;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedCommentRepository;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedRepository;
import com.sorhive.comprojectserver.lifing.command.domain.repository.HoneyRepository;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class : FeedService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           피드 댓글 추가 기능 생성
 * 2022-11-12       부시연           허니 추가 기능 생성
 * 2022-11-12       부시연           허니 삭제 기능 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class FeedService {
    private static final Logger log = LoggerFactory.getLogger(FeedService.class);
    private final TokenProvider tokenProvider;
    private final FeedRepository feedRepository;
    private final FeedCommentRepository feedCommentRepository;
    private final HoneyRepository honeyRepository;
    private final FeedCommentWriterService feedCommentWriterService;

    public FeedService(TokenProvider tokenProvider, FeedRepository feedRepository, FeedCommentRepository feedCommentRepository, HoneyRepository honeyRepository, FeedCommentWriterService feedCommentWriterService) {
        this.tokenProvider = tokenProvider;
        this.feedRepository = feedRepository;
        this.feedCommentRepository = feedCommentRepository;
        this.honeyRepository = honeyRepository;
        this.feedCommentWriterService = feedCommentWriterService;
    }

    /* 피드 댓글 작성 */
    @Transactional
    public ResponseFeedCommentDto createFeedComment(String accessToken, Long feedId, FeedCommentCreateDto feedCommentCreateDto) {

        log.info("[FeedService] createFeedComment Start =========================================================");
        log.info("[FeedService] feedCommentCreateDto : " + feedCommentCreateDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        FeedCommentWriter feedCommentWriter = feedCommentWriterService.createFeedCommentWriter(new MemberCode(memberCode));

        String feedCommentContent = feedCommentCreateDto.getFeedCommentContent();

        Feed feed = feedRepository.findByFeedId(feedId);

        if(feed == null) {
            throw new NoFeedException();
        }

        FeedComment feedComment = new FeedComment(
                feedCommentWriter,
                feedCommentContent,
                feed
        );

        feedCommentRepository.save(feedComment);

        ResponseFeedCommentDto responseFeedCommentDto = new ResponseFeedCommentDto();
        responseFeedCommentDto.setFeedCommentId(feedComment.getId());
        responseFeedCommentDto.setFeedCommentcontent(feedComment.getContent());
        responseFeedCommentDto.setFeedCommentCreateTime(feedComment.getCreateTime());

        return responseFeedCommentDto;

    }

}
