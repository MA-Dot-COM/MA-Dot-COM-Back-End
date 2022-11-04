package com.sorhive.comprojectserver.room.command.domain.model.guestbook;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <pre>
 * Class : GuestBook
 * Comment: 클래스에 대한 간단 설명
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
@Table(name = "tbl_guestbooks")
public class GuestBook {

    @EmbeddedId
    private GuestBookId id;
}
