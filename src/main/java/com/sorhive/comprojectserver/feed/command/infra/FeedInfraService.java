package com.sorhive.comprojectserver.feed.command.infra;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCreateRequestDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCreateResponseDto;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.Feed;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriterService;
import com.sorhive.comprojectserver.feed.command.domain.model.feedimage.FeedImage;
import com.sorhive.comprojectserver.feed.command.domain.repository.FeedRepository;
import com.sorhive.comprojectserver.file.S3FeedImageFile;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <pre>
 * Class : FeedInfraService
 * Comment: 클래스에 대한 간단 설명
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
@Service
@RequiredArgsConstructor
public class FeedInfraService {

    private static final Logger log = LoggerFactory.getLogger(FeedInfraService.class);
    private final TokenProvider tokenProvider;
    private final FeedRepository feedRepository;
    private final FeedWriterService feedWriterService;
    private final S3FeedImageFile s3FeedImageFile;

    /* 피드 생성 */
    public Object createFeed(String accessToken, FeedCreateRequestDto feedCreateRequestDto) {

        log.info("[FeedInfraService] Start =========================================================");
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

}