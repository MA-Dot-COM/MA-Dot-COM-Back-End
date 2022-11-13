package com.sorhive.comprojectserver.chatting.command.domain.model;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : MongoChatting
 * Comment: 몽고 DB 채팅 도메인 모델
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
@Getter
@Document(collection = "chatting")
public class MongoChatting {

    @Id
    private String id;
    private Long roomId;
    private List<Map<String, Object>> chattings;

    protected MongoChatting() {}

    public MongoChatting(Long roomId, List<Map<String, Object>> chattings) {
        this.chattings = chattings;
        this.roomId = roomId;
    }


}
