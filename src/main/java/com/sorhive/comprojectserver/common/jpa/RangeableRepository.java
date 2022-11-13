package com.sorhive.comprojectserver.common.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * <pre>
 * Class : RangeableExecutor
 * Comment: 스프링 데이터 JPA Repository 인터페이스와 RangeableExecutor 인터페이스를 상속.
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
@NoRepositoryBean
public interface RangeableRepository<T, ID extends Serializable>
        extends Repository<T, ID>, RangeableExecutor<T> {
}