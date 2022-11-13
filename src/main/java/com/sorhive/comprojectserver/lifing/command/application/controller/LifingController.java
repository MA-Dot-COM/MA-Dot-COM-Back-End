package com.sorhive.comprojectserver.lifing.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.infra.LifingService;
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
    private final LifingService lifingService;

    public LifingController(LifingService lifingService) {
        this.lifingService = lifingService;
    }

    /** 라이핑 이미지 생성 */
    @PostMapping("lifing/image")
    public ResponseEntity<ResponseDto> lifingImage(@RequestHeader String Authorization, @RequestBody LifingImageDto lifingImageDto) {

        log.info("[LifingController] lifingImage Start ===================");
        log.info("[LifingController] lifingImageDto : " + lifingImageDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 이미지 생성 성공", lifingService.insertLifingImage(accessToken, lifingImageDto)));

    }

    /** 라이핑 생성 */
    @PostMapping("lifing")
    public ResponseEntity<ResponseDto> createLifing(@RequestHeader String Authorization, @RequestBody LifingCreateDto lifingCreateDto) {

        log.info("[LifingController] lifingImage Start ===================");
        log.info("[LifingController] lifingImageDto : " + lifingCreateDto);

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "라이핑 생성 성공", lifingService.createLifing(accessToken, lifingCreateDto)));

    }
}
