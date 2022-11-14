package com.sorhive.comprojectserver.lifing.command.domain.repository;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifingvisit.LifingVisit;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : LifingVisitRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-14       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface LifingVisitRepository extends Repository<LifingVisit, Long> {
    void save(LifingVisit lifingVisit);
}
