package com.sorhive.comprojectserver.common.jpa;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * <pre>
 * Class : RangeableExecutor
 * Comment: Rangeable 타입을 사용하는 조회 메서드 정의
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
public interface RangeableExecutor<T> {
    List<T> getRange(Specification<T> spec, Rangeable rangeable);
}