package com.sorhive.comprojectserver.harvest.query;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * Class : HarvestDataDAO
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
public interface HarvestDataDAO extends JpaRepository<HarvestData, HarvestId> {
}
