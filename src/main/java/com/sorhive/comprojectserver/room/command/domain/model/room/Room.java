package com.sorhive.comprojectserver.room.command.domain.model.room;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Room
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
@Table(name = "tbl_rooms")
public class Room {

    @EmbeddedId
    private RoomId id;

    @Column(name = "wallpaper_path")
    private String wallpaper;

    @Column(name = "floor_path")
    private String floor;

    @Column(name = "room_delete_yn")
    private String deleteYn;

    @Column(name = "room_delete_time")
    private Timestamp deleteTime;

    @Column(name = "room_create_time")
    private Timestamp createTime;


}
