package com.sorhive.comprojectserver.chatting.command.domain.repository;

import com.sorhive.comprojectserver.chatting.command.domain.model.MongoChatting;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <pre>
 * Class : MongoChattingRepository
 * Comment: 몽고 DB 채팅 레포지토리
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
public interface MongoChattingRepository extends MongoRepository<MongoChatting, String> {
}
