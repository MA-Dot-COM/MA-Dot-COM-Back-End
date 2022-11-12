package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
public class QueryHarvestService {

    private HarvestDataDAO harvestDataDAO;

    @Autowired
    public QueryHarvestService(HarvestDataDAO harvestDataDAO) { this.harvestDataDAO = harvestDataDAO; }

    public HarvestData getHarvestData(Long harvestId) {

        Optional<HarvestData> harvestData = harvestDataDAO.findById(new HarvestId(harvestId));

        return harvestData.orElseThrow(() -> new NotFoundHarvestException("해당 하베스트 코드를 조회할 수 없습니다."));
    }
}
