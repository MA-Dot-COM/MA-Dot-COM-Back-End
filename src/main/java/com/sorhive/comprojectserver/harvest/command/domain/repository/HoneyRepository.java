package com.sorhive.comprojectserver.harvest.command.domain.repository;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import com.sorhive.comprojectserver.harvest.command.domain.model.honey.Honey;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : HoneyRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface HoneyRepository extends Repository<Honey, Long> {
    
    void save(Honey honey);
    Optional<Honey> findByMemberCodeAndHarvestIdAndDeleteYnEquals(MemberCode memberCode, HarvestId harvestId, char n);
}
