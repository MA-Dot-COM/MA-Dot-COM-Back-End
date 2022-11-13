package com.sorhive.comprojectserver.chatting.infra;

import com.sorhive.comprojectserver.chatting.command.application.dto.ChattingCreateDto;
import com.sorhive.comprojectserver.chatting.command.domain.model.Chatting;
import com.sorhive.comprojectserver.chatting.command.domain.model.MongoChatting;
import com.sorhive.comprojectserver.chatting.command.domain.repository.ChattingRepository;
import com.sorhive.comprojectserver.chatting.command.domain.repository.MongoChattingRepository;
import com.sorhive.comprojectserver.common.exception.NoContentException;
import com.sorhive.comprojectserver.config.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : ChattingInfraService
 * Comment: 채팅 인프라 서비스(몯고 DB와 통신)
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
@Service
public class ChattingInfraService {
    private static final Logger log = LoggerFactory.getLogger(ChattingInfraService.class);
    private final MongoChattingRepository mongoChattingRepository;
    private final ChattingRepository chattingRepository;
    private final TokenProvider tokenProvider;

    public ChattingInfraService(MongoChattingRepository mongoChattingRepository, ChattingRepository chattingRepository, TokenProvider tokenProvider) {
        this.mongoChattingRepository = mongoChattingRepository;
        this.chattingRepository = chattingRepository;
        this.tokenProvider = tokenProvider;
    }

    public Object createChatting(String accessToken, ChattingCreateDto chattingCreateDto) {

        log.info("[ChattingInfraService] createChatting Start ===================");
        log.info("[ChattingInfraService] chattingCreateDto " + chattingCreateDto );

        Long memberCode = Long.valueOf(tokenProvider.getUserCode(accessToken));

        if((chattingCreateDto.getChatting() ==null ) || (chattingCreateDto.getRoomId() == null)) {
            throw new NoContentException("채팅 정보나 방번호가 존재하질 않습니다.");
        }

        Long roomId = chattingCreateDto.getRoomId();

        /* 몽고 DB 채팅 생성 */
        MongoChatting mongoChatting = new MongoChatting(
                roomId,
                chattingCreateDto.getChatting()
        );

        /* 몽고 DB 채팅 저장하기 */
        mongoChattingRepository.save(mongoChatting);

        /* 채팅 생성하기 */
        Chatting chatting = new Chatting(
                roomId,
                mongoChatting.getId(),
                memberCode
        );

        /* 채팅에 몽고 DB의 아이디 포함하여 저장하기 */
        chattingRepository.save(chatting);
        
        log.info("[ChattingInfraService] createChatting End ====================");
        
        return mongoChatting.getId();

    }
}
