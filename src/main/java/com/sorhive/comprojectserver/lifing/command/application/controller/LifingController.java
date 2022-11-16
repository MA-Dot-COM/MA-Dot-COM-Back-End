package com.sorhive.comprojectserver.lifing.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingAIImageDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCommentCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.application.service.LifingService;
import com.sorhive.comprojectserver.lifing.command.infra.LifingInfraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : LifingController
 * Comment: 라이핑 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           라이핑 이미지 생성 추가
 * 2022-11-12       부시연           라이핑 생성 추가
 * 2022-11-15       부시연           허니 추가
 * 2022-11-15       부시연           허니 제거
 * 2022-11-15       부시연           라이핑 댓글 추가
 * 2022-11-16       부시연           허니 구분을 위해 라이핑 허니로 명칭 변경
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class LifingController {

    private static final Logger log = LoggerFactory.getLogger(LifingController.class);
    private final LifingInfraService lifingInfraService;
    private final LifingService lifingService;

    public LifingController(LifingInfraService lifingInfraService, LifingService lifingService) {
        this.lifingInfraService = lifingInfraService;
        this.lifingService = lifingService;
    }

    /** 라이핑 이미지 생성 */
    @PostMapping("lifing/image")
    public ResponseEntity<ResponseDto> lifingImage(@RequestHeader String Authorization, @RequestBody LifingImageDto lifingImageDto) {

        log.info("[LifingController] lifingImage Start ===================");
        log.info("[LifingController] lifingImageDto : " + lifingImageDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 이미지 생성 성공", lifingInfraService.insertLifingImage(accessToken, lifingImageDto)));

    }

    /** 라이핑 AI 분석 이미지 생성 */
    @PostMapping("lifing/image/ai")
    public ResponseEntity<ResponseDto> lifingAiImage(@RequestHeader String Authorization, @RequestBody LifingAIImageDto lifingAIImageDto) {

        log.info("[LifingController] lifingAiImage Start ===================");
        log.info("[LifingController] lifingImageDto : " + lifingAIImageDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 AI 이미지 생성 성공", lifingInfraService.insertLifingAiImage(accessToken, lifingAIImageDto)));

    }

    /** 라이핑 생성 */
    @PostMapping("lifing")
    public ResponseEntity<ResponseDto> createLifing(@RequestHeader String Authorization, @RequestBody LifingCreateDto lifingCreateDto) {

        log.info("[LifingController] lifingImage Start ===================");
        log.info("[LifingController] lifingImageDto : " + lifingCreateDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 생성 성공", lifingService.createLifing(accessToken, lifingCreateDto)));

    }
    
    /** 라이핑 허니 생성 */
    @PostMapping("lifing/honey/{lifingId}")
    public ResponseEntity<ResponseDto> createLifingHoney(@RequestHeader String Authorization, @PathVariable Long lifingId) {

        log.info("[LifingController] createLifingHoney Start ============================");
        log.info("[lifingId] " + lifingId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 허니 추가 성공", lifingService.createLifingHoney(accessToken, lifingId)));

    }

    /** 라이핑 허니 제거 */
    @DeleteMapping("lifing/honey/{lifingId}")
    public ResponseEntity<ResponseDto> deleteLifingHoney(@RequestHeader String Authorization, @PathVariable Long lifingId) {

        log.info("[LifingController] deleteLifingHoney Start ============================");
        log.info("[lifingId] " + lifingId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.NO_CONTENT, "라이핑 허니 삭제 성공", lifingService.deleteLifingHoney(accessToken, lifingId)));

    }

    /** 라이핑 댓글 작성 */
    @PostMapping("lifing/comment/{lifingId}")
    public ResponseEntity<ResponseDto> createLifingComment(@RequestHeader String Authorization, @PathVariable Long lifingId, @RequestBody LifingCommentCreateDto lifingCommentCreateDto) {

        log.info("[LifingController] createLifingComment Start ============================");
        log.info("[lifingId] " + lifingId);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 댓글 추가 성공", lifingService.createLifingComment(accessToken, lifingId, lifingCommentCreateDto)));

    }
    
}
