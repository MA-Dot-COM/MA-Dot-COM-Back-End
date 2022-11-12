package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.harvest.query.dto.HarvestRequestDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : HarvestQueryService
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
@Service
@AllArgsConstructor
public class QueryHarvestService {

    private HarvestMapper harvestMapper;
    private static final Logger log = LoggerFactory.getLogger(QueryHarvestService.class);

    public Object selectAllHarvest(HarvestRequestDto harvestRequestDto) {

        Long memberCode = harvestRequestDto.getMemberCode();
        int pageNo = harvestRequestDto.getPageNo() - 1;

        return harvestMapper.selectAllHarvest(memberCode, pageNo);
    }

    public Object selectHarvestDetail() {

        return null;
    }
}
