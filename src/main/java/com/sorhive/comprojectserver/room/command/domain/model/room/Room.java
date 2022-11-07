package com.sorhive.comprojectserver.room.command.domain.model.room;

import javax.persistence.*;
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

    @Id
    @Column(name="room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "wallpaper_path")
//    private String wallpaper;
//
//    @Column(name = "floor_path")
//    private String floor;

    @Column(columnDefinition = "json", name = "room_info")
    private String roomInfo;

//    @Column(name = "room_delete_yn")
//    private String deleteYn;
//
//    @Column(name = "room_delete_time")
//    private Timestamp deleteTime;

    @Embedded
    private RoomCreator roomCreator;

    @Column(name = "room_create_time")
    private Timestamp createTime;

    @Column(name = "room_upload_time")
    private Timestamp uploadTime;

    protected Room() {}

    public Room(Long id, String roomInfo, RoomCreator roomCreator, Timestamp createTime, Timestamp uploadTime) {
        this.id = id;
        this.roomInfo = roomInfo;
        this.roomCreator = roomCreator;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
    }

    public Room(String roomInfo, RoomCreator roomCreator) {
        setRoomInfo(roomInfo);
        setRoomCreator(roomCreator);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() { return id; }

    public RoomCreator getRoomCreator() { return roomCreator; }

    public void setRoomCreator(RoomCreator roomCreator) { this.roomCreator = roomCreator; }

    public String getRoomInfo() { return roomInfo; }

    public void setRoomInfo(String roomInfo) { this.roomInfo = roomInfo; }

    public Timestamp getUploadTime() { return uploadTime; }

    public Timestamp getCreateTime() {
        return createTime;
    }

}
