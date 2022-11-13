package com.sorhive.comprojectserver.room.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 * Class : GuestBookCreateResponseDto
 * Comment: 방명록 생성 응답 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class GuestBookCreateResponseDto {

    private Long guestBookId;
    private String guestBookContent;
    private Long guestBookWriterCode;
    private String guestBookWriterId;
    private String guestBookWriterName;
    private Long roomId;
    private Date createTime;

}
