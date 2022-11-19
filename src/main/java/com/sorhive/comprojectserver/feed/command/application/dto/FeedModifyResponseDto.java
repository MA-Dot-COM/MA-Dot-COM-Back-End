package com.sorhive.comprojectserver.feed.command.application.dto;

import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feedimage.FeedImage;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

/**
 * <pre>
 * Class : FeedModifyResponseDto
 * Comment: 피드 수정 응답 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-20       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class FeedModifyResponseDto {

    private Long feedId;
    private FeedWriter feedWriter;
    private String feedContent;
    private Timestamp feedUploadTime;
    private List<FeedImage> feedImages;

    public FeedModifyResponseDto(Long feedId, FeedWriter feedWriter, String feedContent, Timestamp feedUploadTime, List<FeedImage> feedImages) {

        this.feedId = feedId;
        this.feedContent = feedContent;
        this.feedWriter = feedWriter;
        this.feedUploadTime = feedUploadTime;
        this.feedImages = feedImages;

    }
}
