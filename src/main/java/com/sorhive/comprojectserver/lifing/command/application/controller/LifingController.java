package com.sorhive.comprojectserver.lifing.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingCreateDto;
import com.sorhive.comprojectserver.lifing.command.application.dto.LifingImageDto;
import com.sorhive.comprojectserver.lifing.command.infra.LifingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : LifingController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
public class LifingController {

    private final LifingService lifingService;

    public LifingController(LifingService lifingService) {
        this.lifingService = lifingService;
    }

    @PostMapping("lifing/image")
    public ResponseEntity<ResponseDto> lifingImage(@RequestHeader String Authorization, @RequestBody LifingImageDto lifingImageDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "라이핑 이미지 생성 성공", lifingService.insertLifingImage(accessToken, lifingImageDto)));

    }

    @PostMapping("lifing")
    public ResponseEntity<ResponseDto> createLifing(@RequestHeader String Authorization, @RequestBody LifingCreateDto lifingCreateDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "라이핑 생성 성공", lifingService.createLifing(accessToken, lifingCreateDto)));

    }
}
