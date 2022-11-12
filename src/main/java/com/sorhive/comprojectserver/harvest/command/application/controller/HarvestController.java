package com.sorhive.comprojectserver.harvest.command.application.controller;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCommentCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.dto.HarvestCreateDto;
import com.sorhive.comprojectserver.harvest.command.application.service.HarvestService;
import com.sorhive.comprojectserver.harvest.command.infra.HarvestInfraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * Class : HarvestController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           하베스트 생성 추가
 * 2022-11-12       부시연           하브스트 댓글 생성 추가
 * 2022-11-12       부시연           허니 생성 추가
 * 2022-11-12       부시연           허니 삭제 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class HarvestController {

    private final HarvestInfraService harvestInfraService;

    private final HarvestService harvestService;

    @PostMapping("harvest")
    public ResponseEntity<ResponseDto> createHarvest(@RequestHeader String Authorization, @RequestBody HarvestCreateDto harvestCreateDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "하베스트 생성 성공", harvestInfraService.createHarvest(accessToken, harvestCreateDto)));

    }

    @PostMapping("harvest/comment/{harvestId}")
    public ResponseEntity<ResponseDto> createHarvestComment(@RequestHeader String Authorization, @PathVariable Long harvestId, @RequestBody HarvestCommentCreateDto harvestCommentCreateDto) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "하베스트 댓글 추가 성공", harvestService.createHarvestComment(accessToken, harvestId, harvestCommentCreateDto)));

    }
    
    @PostMapping("honey/{harvestId}")
    public ResponseEntity<ResponseDto> createHoney(@RequestHeader String Authorization, @PathVariable Long harvestId) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "허니 추가 성공", harvestService.createHoney(accessToken, harvestId)));

    }

    @DeleteMapping("honey/{harvestId}")
    public ResponseEntity<ResponseDto> deleteHoney(@RequestHeader String Authorization, @PathVariable Long harvestId) {

        String accessToken = Authorization.substring(7);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "허니 삭제 성공", harvestService.deleteHoney(accessToken, harvestId)));

    }
}
