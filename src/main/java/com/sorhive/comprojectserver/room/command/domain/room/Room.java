package com.sorhive.comprojectserver.room.command.domain.room;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.placedfurniture.PlacedFurniture;
import com.sorhive.comprojectserver.room.command.domain.roomvisit.RoomVisit;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "wall_number")
    private Long wallNumber;

    @Column(name = "floor_number")
    private Long floorNumber;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<PlacedFurniture> placedFurnitures = new ArrayList<PlacedFurniture>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<GuestBook> guestBooks = new ArrayList<GuestBook>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomVisit> roomVisits = new ArrayList<RoomVisit>();

    @Column(name = "room_delete_yn")
    private String deleteYn;

    @Column(name = "room_delete_time")
    private Timestamp deleteTime;

    @Embedded
    private RoomCreator roomCreator;

    @Column(name = "room_create_time")
    private Timestamp createTime;

    @Column(name = "room_upload_time")
    private Timestamp uploadTime;

    protected Room() {}

    public Room(Long id, RoomCreator roomCreator, Timestamp createTime, Timestamp uploadTime) {
        this.id = id;
        this.roomCreator = roomCreator;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
    }

    public Room(RoomCreator roomCreator) {
        setRoomCreator(roomCreator);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() { return id; }

    public RoomCreator getRoomCreator() { return roomCreator; }

    public void setRoomCreator(RoomCreator roomCreator) { this.roomCreator = roomCreator; }

    public Timestamp getUploadTime() { return uploadTime; }

    public Timestamp getCreateTime() {
        return createTime;
    }

}
