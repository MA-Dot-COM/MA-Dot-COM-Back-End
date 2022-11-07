package com.sorhive.comprojectserver.member.query;

import com.sorhive.comprojectserver.common.jpa.Rangeable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : MemberDataDAO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface MemberDataDao extends Repository<MemberData, String> {

    MemberData findById(String memberId);
    List<MemberData> findByNameLike(String name, Pageable pageable);

    List<MemberData> findAll(Specification<MemberData> spec, Pageable pageable);

    List<MemberData> getRange(Specification<MemberData> spec, Rangeable rangeable);

    List<MemberData> findFirst3ByNameLikeOrderByName(String name);
    Optional<MemberData> findFirstByNameLikeOrderByName(String name);

    MemberData findByMemberCode(Long memberCode);
}
