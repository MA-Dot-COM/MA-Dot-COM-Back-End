package com.sorhive.comprojectserver.harvest.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * <pre>
 * Class : ResponseHarvestDto
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
@Getter
@Setter
public class ResponseHarvestDto {

    private Long harvestId;
    private String harvestContent;
    private List<String> harvestImagePath;
    private Timestamp harvestCreateTime;
}
