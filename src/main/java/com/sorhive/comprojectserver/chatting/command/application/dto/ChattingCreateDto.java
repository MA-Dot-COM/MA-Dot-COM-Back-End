package com.sorhive.comprojectserver.chatting.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : ChattingCreateDto
 * Comment: 채팅 생성 요청 전송 객체
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
@AllArgsConstructor
@Getter
public class ChattingCreateDto {

    private final Long roomId;
    private List<Map<String,Object>> chatting;

}
