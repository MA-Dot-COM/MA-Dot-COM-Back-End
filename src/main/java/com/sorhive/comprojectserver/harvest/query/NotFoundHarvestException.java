package com.sorhive.comprojectserver.harvest.query;

/**
 * <pre>
 * Class : NotFoundHarvestException
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
public class NotFoundHarvestException extends RuntimeException{

    public NotFoundHarvestException(String msg) { super(msg); }
}
