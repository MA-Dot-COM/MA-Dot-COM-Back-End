package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.common.ResponseDto;
import com.sorhive.comprojectserver.harvest.query.dto.HarvestRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("api/v1")
public class QueryHarvestController {

    private static final Logger log = LoggerFactory.getLogger(QueryHarvestController.class);
    private final QueryHarvestService queryHarvestService;

    public QueryHarvestController(QueryHarvestService queryHarvestService) {
        this.queryHarvestService = queryHarvestService;
    }

    @GetMapping("harvest")
    public ResponseEntity<ResponseDto> selectAllHarvest(@RequestBody HarvestRequestDto harvestRequestDto) {

        return ResponseEntity.ok().body(
                new ResponseDto(HttpStatus.OK
                        , "하베스트 조회 성공"
                        , queryHarvestService.selectAllHarvest(harvestRequestDto)));

    }

    @GetMapping("harvest/{harvestId}")
    public ResponseEntity<ResponseDto> selectHarvestDetail(@PathVariable Long harvestId) {

        log.info("HarvestId:: " + harvestId);

        return ResponseEntity.ok().body(
                new ResponseDto(HttpStatus.OK
                        , "하베스트 상세 조회 성공"
                        , queryHarvestService.selectHarvestDetail(harvestId)));

    }
}
