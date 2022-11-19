package com.sorhive.comprojectserver.feed.command.infra;

import com.sorhive.comprojectserver.common.exception.AlreadyDeleteException;
import com.sorhive.comprojectserver.common.exception.NotSameWriterException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCreateRequestDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCreateResponseDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedModifyRequestDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedModifyResponseDto;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.Feed;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriterService;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedComment;
import com.sorhive.comprojectserver.feed.command.domain.model.feedimage.FeedImage;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedCommentRepository;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedImageRepository;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedRepository;
import com.sorhive.comprojectserver.feed.exception.NoFeedException;
import com.sorhive.comprojectserver.feed.exception.NoRequiredFeedModifyException;
import com.sorhive.comprojectserver.file.S3FeedImageFile;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * <pre>
 * Class : FeedInfraService
 * Comment: 피드 인프라 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           피드 생성
 * 2022-11-19       부시연           피드 삭제
 * 2022-11-20       부시연           피드 수정
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class FeedInfraService {

    private static final Logger log = LoggerFactory.getLogger(FeedInfraService.class);
    private final TokenProvider tokenProvider;
    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedCommentRepository feedCommentRepository;
    private final FeedWriterService feedWriterService;
    private final S3FeedImageFile s3FeedImageFile;

    public FeedInfraService(TokenProvider tokenProvider, FeedRepository feedRepository, FeedImageRepository feedImageRepository, FeedCommentRepository feedCommentRepository, FeedWriterService feedWriterService, S3FeedImageFile s3FeedImageFile) {
        this.tokenProvider = tokenProvider;
        this.feedRepository = feedRepository;
        this.feedImageRepository = feedImageRepository;
        this.feedCommentRepository = feedCommentRepository;
        this.feedWriterService = feedWriterService;
        this.s3FeedImageFile = s3FeedImageFile;
    }

    /** 피드 생성 */
    @Transactional
    public Object createFeed(String accessToken, FeedCreateRequestDto feedCreateRequestDto) {

        log.info("[FeedInfraService] createFeed Start =========================================================");
        log.info("[FeedInfraService] feedCreateDto : " + feedCreateRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        /* 피드 작성자 생성하기 */
        FeedWriter feedWriter = feedWriterService.createFeedWriter(new MemberCode(memberCode));
        String feedContent = feedCreateRequestDto.getFeedContent();

        /* 피드 생성 */
        Feed feed = new Feed(
                feedWriter,
                feedContent
        );

        /* 피드 저장하기 */
        feedRepository.save(feed);

        /* 응답 객체 생성 */
        FeedCreateResponseDto feedCreateResponseDto = new FeedCreateResponseDto(
                feed.getFeedId(),
                feed.getFeedCreateTime(),
                feed.getFeedContent()
        );

        try {

            /* 피드에 이미지가 있다면 */
            if(feedCreateRequestDto.getFeedImage() != null) {

                List<String> feedImagePathList = new ArrayList<>();

                /* 이미지들을 하나씩 꺼내오기 */
                for (int i = 0; i < feedCreateRequestDto.getFeedImage().size(); i++) {

                    byte[] feedByteImage = feedCreateRequestDto.getFeedImage().get(i).getFeedImage();
                    String originalName = feedCreateRequestDto.getFeedImage().get(i).getFeedImageName();
                    String changeName = UUID.randomUUID() + "feed.png";
                    String feedImagePath = s3FeedImageFile.upload(feedByteImage, changeName, "images");

                    /* 피드 이미지 생성 */
                    FeedImage feedImage = new FeedImage(
                            feedImagePath,
                            originalName,
                            changeName,
                            feed
                    );

                    /* 피드 이미지 경로 저장 */
                    feedImagePathList.add(feedImagePath);

                }

                feedCreateResponseDto.setFeedImagePath(feedImagePathList);

                return feedCreateResponseDto;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return feedCreateResponseDto;
    }

    /** 피드 수정 */
    @Transactional
    public FeedModifyResponseDto modifyFeed(String accessToken, FeedModifyRequestDto feedModifyRequestDto) {

        log.info("[FeedInfraService] modifyFeed Start =========================================================");
        log.info("[FeedInfraService] feedModifyRequestDto : " + feedModifyRequestDto);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));
        Long feedId = feedModifyRequestDto.getFeedId();
        String feedContent = feedModifyRequestDto.getFeedContent();

        if((feedId == null) || (feedContent == null)) {
            log.warn("[FeedService] NoFeedExecption");
            throw new NoRequiredFeedModifyException("해당 수정 요청에는 필요한 정보가 없습니다.");
        }

        if(feedRepository.findByFeedId(feedId) == null) {
            log.warn("[FeedService] NoFeedException");
            throw new NoFeedException("해당 피드는 존재하지 않습니다.");
        }

        if(feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N').isEmpty()) {
            log.warn("[FeedService] NoFeedException");
            throw new AlreadyDeleteException("해당 피드는 이미 삭제되었습니다.");
        }

        Optional<Feed> feedData = feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N');
        Feed feed = feedData.get();

        if(feed.getFeedWriter().getMemberCode().getValue() != memberCode) {
            log.warn("[FeedService] NotSameWriterException");
            throw new NotSameWriterException("해당 피드 작성자와 삭제 요청자가 다릅니다.");
        }

        feed.modify(feedContent);
        feedRepository.save(feed);

        /* 기존 이미지 삭제 요청이 존재한다면 삭제 처리 */
        if(feedModifyRequestDto.getFeedImageNos() != null) {

            List<Long> feedImageNos = feedModifyRequestDto.getFeedImageNos();

            for (int i = 0; i < feedImageNos.size(); i++) {

                FeedImage feedImage = feedImageRepository.findById(feedImageNos.get(i));

                feedImage.deleteImage();
                feedImageRepository.save(feedImage);
            }
            
        }

        /* 피드 수정 요청에 추가할 이미지가 있다면 */
        if((feedModifyRequestDto.getFeedImage() != null) && (feedModifyRequestDto.getFeedImageName() != null)) {

            try {

                byte[] feedByteImage = feedModifyRequestDto.getFeedImage();
                String originalName = feedModifyRequestDto.getFeedImageName();
                String changeName = UUID.randomUUID() + "feed.png";
                String feedImagePath = s3FeedImageFile.upload(feedByteImage, changeName, "images");

                /* 피드 이미지 생성 */
                FeedImage feedImage = new FeedImage(
                        feedImagePath,
                        originalName,
                        changeName,
                        feed
                );

                feedImageRepository.save(feedImage);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        FeedModifyResponseDto feedModifyResponseDto;

        feedModifyResponseDto = new FeedModifyResponseDto(
                feed.getFeedId(),
                feed.getFeedWriter(),
                feed.getFeedContent(),
                feed.getFeedUploadTime(),
                feed.getFeedImages()
        );

        return feedModifyResponseDto;

    }

    /** 피드 삭제 */
    @Transactional
    public Object deleteFeed(String accessToken, Long feedId) {

        log.info("[FeedService] deleteFeed Start =========================================================");
        log.info("[FeedService] feedId : " + feedId);

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if(feedRepository.findByFeedId(feedId) == null) {
            log.warn("[FeedService] NoFeedException");
            throw new NoFeedException("해당 피드는 존재하지 않습니다.");
        }

        if(feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N').isEmpty()) {
            log.warn("[FeedService] NoFeedException");
            throw new AlreadyDeleteException("해당 피드는 이미 삭제되었습니다.");
        }

        Optional<Feed> feedData = feedRepository.findByFeedIdAndFeedDeleteYnEquals(feedId, 'N');
        Feed feed = feedData.get();

        if(feed.getFeedWriter().getMemberCode().getValue() != memberCode) {
            log.warn("[FeedService] NotSameWriterException");
            throw new NotSameWriterException("해당 피드 작성자와 삭제 요청자가 다릅니다.");
        }

        feed.delete();

        /* 해당 피드에 대한 댓글이 있다면 */
        if(!feedCommentRepository.findByFeed(feed).isEmpty()) {
            List<FeedComment> feedCommentList = feedCommentRepository.findByFeed(feed);

            for (int i = 0; i < feedCommentList.size(); i++) {
                feedCommentList.get(i).delete();
                feedCommentRepository.save(feedCommentList.get(i));
            }
        }

        /* 기존 이미지가 존재한다면 삭제 처리 */
        if(!feedImageRepository.findByFeed(feed).isEmpty()) {
            List<FeedImage> feedImages = feedImageRepository.findByFeed(feed);

            for (int i = 0; i < feedImages.size(); i++) {

                /* 하나씩 꺼내서 삭제 여부를 Y로 바꾸어준다. */
                feedImages.get(i).deleteImage();
                feedImageRepository.save(feedImages.get(i));
            }
        }

        log.info("[FeedService] deleteFeed End =========================================================");

        return feed.getFeedId();

    }
}