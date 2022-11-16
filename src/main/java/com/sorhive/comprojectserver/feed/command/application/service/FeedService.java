package com.sorhive.comprojectserver.feed.command.application.service;

import com.sorhive.comprojectserver.common.exception.AlreadyDeleteException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCommentCreateDto;
import com.sorhive.comprojectserver.feed.command.application.dto.ResponseFeedCommentDto;
import com.sorhive.comprojectserver.feed.command.application.dto.ResponseFeedHoneyCreateDto;
import com.sorhive.comprojectserver.feed.command.application.dto.ResponseFeedHoneyDeleteDto;
import com.sorhive.comprojectserver.feed.command.application.exception.NoFeedException;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.Feed;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedId;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedComment;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriterService;
import com.sorhive.comprojectserver.feed.command.domain.model.feedhoney.FeedHoney;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedCommentRepository;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedHoneyRepository;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedRepository;
import com.sorhive.comprojectserver.lifing.command.application.exception.AlreadyHoneyException;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
 * 2022-11-16       부시연           피드 허니 추가 기능 추가
 * 2022-11-16       부시연           피드 허니 제거 기능 추가
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
    private final FeedHoneyRepository feedHoneyRepository;
    private final FeedCommentWriterService feedCommentWriterService;

    public FeedService(TokenProvider tokenProvider, FeedRepository feedRepository, FeedCommentRepository feedCommentRepository, FeedHoneyRepository feedHoneyRepository, FeedHoneyRepository feedhoneyRepository, FeedHoneyRepository feedHoneyRepository1, FeedCommentWriterService feedCommentWriterService) {
        this.tokenProvider = tokenProvider;
        this.feedRepository = feedRepository;
        this.feedCommentRepository = feedCommentRepository;
        this.feedHoneyRepository = feedHoneyRepository1;
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

    /* 허니 추가 */
    @Transactional
    public ResponseFeedHoneyCreateDto createFeedHoney(String accessToken, Long feedId) {

        log.info("[FeedService] createFeedHoney Start =========================================================");
        log.info("[FeedService] feedId : " + feedId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(feedRepository.findByFeedId(feedId) == null) {
            throw new NoFeedException("해당 피드가 존재하지 않습니다.");
        }

        Optional<Feed> feedData = feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N');

        if(!feedHoneyRepository.findByMemberCodeAndFeedIdAndDeleteYnEquals(new MemberCode(memberCode), new FeedId(feedId), 'N').isEmpty() ) {
            throw new AlreadyHoneyException("이미 허니를 했습니다.");
        }

        Feed feed = feedData.get();
        feed.countingFeedHoney(1);
        feedRepository.save(feed);

        FeedHoney feedHoney = new FeedHoney(
                new MemberCode(memberCode),
                new FeedId(feedId)
        );

        feedHoneyRepository.save(feedHoney);

        ResponseFeedHoneyCreateDto responseFeedHoneyCreateDto = new ResponseFeedHoneyCreateDto();
        responseFeedHoneyCreateDto.setFeedHoneyId(feedHoney.getId());
        responseFeedHoneyCreateDto.setFeedId(feedHoney.getFeedId().getValue());
        responseFeedHoneyCreateDto.setMemberCode(feedHoney.getMemberCode().getValue());
        responseFeedHoneyCreateDto.setCreateTime(feedHoney.getCreateTime());

        return responseFeedHoneyCreateDto;

    }

    /* 허니 취소 */
    @Transactional
    public ResponseFeedHoneyDeleteDto deleteFeedHoney(String accessToken, Long feedId) {

        log.info("[FeedService] deleteFeedHoney Start =========================================================");
        log.info("[FeedService] feedId : " + feedId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(feedRepository.findByFeedId(feedId) == null) {
            throw new NoFeedException("해당 피드가 존재하지 않습니다.");
        }

        Optional<Feed> feedData = feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N');

        Feed feed = feedData.get();
        feed.countingFeedHoney(-1);
        feedRepository.save(feed);

        if(feedHoneyRepository.findByMemberCodeAndFeedIdAndDeleteYnEquals(new MemberCode(memberCode), new FeedId(feedId), 'N').isEmpty() ) {
            throw new AlreadyDeleteException("이미 피드 허니가 취소 되어있습니다");
        }

        Optional<FeedHoney> honeyData = feedHoneyRepository.findByMemberCodeAndFeedIdAndDeleteYnEquals(new MemberCode(memberCode), new FeedId(feedId), 'N');

        FeedHoney feedHoney = honeyData.get();


        feedHoney.setDeleteYn('Y');
        feedHoneyRepository.save(feedHoney);

        ResponseFeedHoneyDeleteDto responseFeedHoneyDeleteDto = new ResponseFeedHoneyDeleteDto();
        responseFeedHoneyDeleteDto.setFeedHoneyId(feedHoney.getId());
        responseFeedHoneyDeleteDto.setFeedId(feedHoney.getFeedId().getValue());
        responseFeedHoneyDeleteDto.setMemberCode(feedHoney.getMemberCode().getValue());
        responseFeedHoneyDeleteDto.setDeleteTime(feedHoney.getDeleteTime());

        return responseFeedHoneyDeleteDto;

    }

}
