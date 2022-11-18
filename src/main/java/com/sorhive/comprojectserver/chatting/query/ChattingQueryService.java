package com.sorhive.comprojectserver.chatting.query;

import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : ChattingQueryService
 * Comment: 채팅 조회 서비스
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
@Service
public class ChattingQueryService {

    private static final Logger log = LoggerFactory.getLogger(ChattingQueryService.class);
    private final TokenProvider tokenProvider;
    private final ChattingMapper chattingMapper;
    private final ChattingQueryRepository chattingQueryRepository;
    private final MongoChattingQueryRepository mongoChattingQueryRepository;

    public ChattingQueryService(TokenProvider tokenProvider, ChattingMapper chattingMapper, ChattingQueryRepository chattingQueryRepository, MongoChattingQueryRepository mongoChattingQueryRepository) {
        this.tokenProvider = tokenProvider;
        this.chattingMapper = chattingMapper;
        this.chattingQueryRepository = chattingQueryRepository;
        this.mongoChattingQueryRepository = mongoChattingQueryRepository;
    }

    /** 자신의 채팅 목록 불러오기 */
    public Object findChattingList(String accessToken) {

        log.info("[ChattingQueryService] Start ==============================");

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        List<MongoChattingData> chattingData = mongoChattingQueryRepository.findByMemberCode1OrMemberCode2OrderByUploadTimeDesc(memberCode, memberCode);



        return chattingData;
    }
}
