package com.sorhive.comprojectserver.chatting.query;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * Class : ChattingMapper
 * Comment: 채팅 매퍼
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-18       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Mapper
public interface ChattingMapper {
    List<ChattingData> findChattingList(Long memberCode1, Long memberCode2);
}
