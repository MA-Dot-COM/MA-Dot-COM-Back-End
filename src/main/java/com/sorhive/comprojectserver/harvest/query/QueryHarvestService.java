package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment.NoHarvestException;
import com.sorhive.comprojectserver.harvest.query.dto.HarvestCommentSummary;
import com.sorhive.comprojectserver.harvest.query.dto.HarvestImageSummary;
import com.sorhive.comprojectserver.harvest.query.dto.HarvestRequestDto;
import com.sorhive.comprojectserver.harvest.query.dto.HarvestResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if(harvestMapper.selectAllHarvest(memberCode, pageNo) == null) {
            throw new NoHarvestException();
        }

        return harvestMapper.selectAllHarvest(memberCode, pageNo);
    }

    public Object selectHarvestDetail(Long harvestId) {

        HarvestResponseDto harvestResponseDto = new HarvestResponseDto();

        if(harvestMapper.selectHarvestByHarvestId(harvestId) == null) {
            throw new NoHarvestException();
        }

        harvestResponseDto.setHarvestSummary(harvestMapper.selectHarvestByHarvestId(harvestId));

        if(harvestMapper.selectAllHarvestImages(harvestId) != null) {
            List<HarvestImageSummary> harvestImageSummaryList = harvestMapper.selectAllHarvestImages(harvestId);

            harvestResponseDto.setHarvestImageSummaryList(harvestImageSummaryList);
        }

        if(harvestMapper.selectAllHarvestComments(harvestId) != null) {
            List<HarvestCommentSummary> harvestCommentSummaries = harvestMapper.selectAllHarvestComments(harvestId);

            harvestResponseDto.setHarvestSummaryList(harvestCommentSummaries);
        }


        return harvestResponseDto;
    }
}
