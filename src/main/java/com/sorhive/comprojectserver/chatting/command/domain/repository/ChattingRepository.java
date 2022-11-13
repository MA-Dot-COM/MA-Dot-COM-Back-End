package com.sorhive.comprojectserver.chatting.command.domain.repository;

import com.sorhive.comprojectserver.chatting.command.domain.model.Chatting;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : ChattingRepository
 * Comment: 채팅 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface ChattingRepository extends Repository<Chatting, Long> {
    void save(Chatting chatting);

}
