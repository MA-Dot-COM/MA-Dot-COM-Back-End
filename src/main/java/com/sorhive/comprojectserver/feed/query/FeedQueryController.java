package com.sorhive.comprojectserver.feed.query;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.feed.query.dto.FeedRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * <pre>
 * Class : FeedQueryController
 * Comment: 피드 조회용 컨트롤러
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
@RestController
@RequestMapping("api/v1")
public class FeedQueryController {

    private static final Logger log = LoggerFactory.getLogger(FeedQueryController.class);
    private final FeedQueryService feedQueryService;

    public FeedQueryController(FeedQueryService feedQueryService) {
        this.feedQueryService = feedQueryService;
    }

    /* 피드 목록 조회 */
    @GetMapping("feed")
    public ResponseEntity<ResponseDto> selectAllFeed(@RequestBody FeedRequestDto feedRequestDto) {

        return ResponseEntity.ok().body(
                new ResponseDto(HttpStatus.OK
                        , "피드 조회 성공"
                        , feedQueryService.selectAllFeed(feedRequestDto)));

    }

    @GetMapping("feed/{feedId}")
    public ResponseEntity<ResponseDto> selectFeedDetail(@PathVariable Long feedId) {

        log.info("feedId:: " + feedId);

        return ResponseEntity.ok().body(
                new ResponseDto(HttpStatus.OK
                        , "피드 상세 조회 성공"
                        , feedQueryService.selectFeedDetail(feedId)));

    }
}
