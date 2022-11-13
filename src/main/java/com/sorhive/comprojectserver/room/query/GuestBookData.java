package com.sorhive.comprojectserver.room.query;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <pre>
 * Class : GuestBookData
 * Comment: 클래스에 대한 간단 설명
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
@Entity
@Table(name = "tbl_guestbooks")
@Getter
public class GuestBookData {

    @Id
    @Column(name="guestbook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guestbook_content")
    private String content;

    @Column(name = "guestbook_writer_code")
    private Long memberCode;

    @Column(name= "room_id")
    private Long roomId;

    @Column(name = "guestbook_create_time")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "guestbook_upload_time")
    private Timestamp uploadTime;

    @Column(name = "guestbook_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

}
