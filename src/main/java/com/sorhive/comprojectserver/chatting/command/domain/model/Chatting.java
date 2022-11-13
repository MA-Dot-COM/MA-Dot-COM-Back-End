package com.sorhive.comprojectserver.chatting.command.domain.model;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Chatting
 * Comment: 채팅 도메인 모델
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_chatting")
@Getter
public class Chatting {

    @Id
    @Column(name="chatting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatting_room_id")
    private Long roomId;

    @Column(name = "chatting_no")
    private String chattingNo;

    @Column(name = "chatting_member_code")
    private Long memberCode;

    @Column(name = "chatting_upload_time")
    private Timestamp uploadTime;

    protected Chatting() { }

    public Chatting(Long roomId, String chattingNo, Long memberCode) {

        this.roomId = roomId;
        this.chattingNo = chattingNo;
        this.memberCode = memberCode;
        this.uploadTime = new Timestamp(System.currentTimeMillis());

    }
}
