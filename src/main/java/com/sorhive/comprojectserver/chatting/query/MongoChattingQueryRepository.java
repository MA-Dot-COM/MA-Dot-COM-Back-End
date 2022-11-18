package com.sorhive.comprojectserver.chatting.query;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <pre>
 * Class : MongoChattingQueryRepository
 * Comment: 몽고 DB 채팅 조회 레포지토리
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
public interface MongoChattingQueryRepository extends MongoRepository<MongoChattingData, String> {
    List<MongoChattingData> findByMemberCode1OrMemberCode2OrderByUploadTimeDesc(Long memberCode, Long memberCode1);
}
