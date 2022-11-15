package com.sorhive.comprojectserver.feed.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCommentCreateDto;
import com.sorhive.comprojectserver.feed.command.application.dto.FeedCreateDto;
import com.sorhive.comprojectserver.feed.command.application.service.FeedService;
import com.sorhive.comprojectserver.feed.command.infra.FeedInfraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : FeedController
 * Comment: 피드 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           피드 생성 추가
 * 2022-11-12       부시연           피드 댓글 생성 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class FeedController {

    private static final Logger log = LoggerFactory.getLogger(FeedController.class);
    private final FeedInfraService feedInfraService;
    private final FeedService feedService;

    public FeedController(FeedInfraService feedInfraService, FeedService feedService) {
        this.feedInfraService = feedInfraService;
        this.feedService = feedService;
    }

    /* 피드 작성 */
    @PostMapping("feed")
    public ResponseEntity<ResponseDto> createFeed(@RequestHeader String Authorization, @RequestBody FeedCreateDto feedCreateDto) {

        log.info("[FeedController] createFeed Start ============================");
        log.info("[feedCreateDto] " + feedCreateDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "피드 생성 성공", feedInfraService.createFeed(accessToken, feedCreateDto)));

    }

    /* 피드 댓글 작성 */
    @PostMapping("feed/comment/{feedId}")
    public ResponseEntity<ResponseDto> createFeedComment(@RequestHeader String Authorization, @PathVariable Long feedId, @RequestBody FeedCommentCreateDto feedCommentCreateDto) {

        log.info("[FeedController] createFeedComment Start ============================");
        log.info("[feedId] " + feedId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "피드 댓글 추가 성공", feedService.createFeedComment(accessToken, feedId, feedCommentCreateDto)));

    }

}
