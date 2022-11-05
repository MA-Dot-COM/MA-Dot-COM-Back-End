package com.sorhive.comprojectserver.harvest.command.domain.repository;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.Harvest;
import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : FeedRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface HarvestRepository extends Repository<Harvest, HarvestId> {

    Optional<Harvest> findById(HarvestId id);
}
