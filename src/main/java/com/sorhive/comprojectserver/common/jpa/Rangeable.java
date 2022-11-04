package com.sorhive.comprojectserver.common.jpa;

import org.springframework.data.domain.Sort;

/**
 * <pre>
 * Class : Rangeable
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
public class Rangeable {

    private int start;
    private int limit;
    private Sort sort;

    public Rangeable(int start, int limit, Sort sort) {
        this.start = start;
        this.limit = limit;
        this.sort = sort;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public Sort getSort() {
        return sort;
    }

    public static Rangeable of(int start, int limit) {
        return new Rangeable(start, limit, Sort.unsorted());
    }
}
