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
    @Column(name="room_id", unique = true)
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

    public Room(Long id, Long wallNumber, Long floorNumber, List<PlacedFurniture> placedFurnitures, List<GuestBook> guestBooks, List<RoomVisit> roomVisits, String deleteYn, Timestamp deleteTime, RoomCreator roomCreator, Timestamp createTime, Timestamp uploadTime) {
        this.id = id;
        this.wallNumber = wallNumber;
        this.floorNumber = floorNumber;
        this.placedFurnitures = placedFurnitures;
        this.guestBooks = guestBooks;
        this.roomVisits = roomVisits;
        this.deleteYn = deleteYn;
        this.deleteTime = deleteTime;
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

    public Long getWallNumber() {
        return wallNumber;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public List<PlacedFurniture> getPlacedFurnitures() {
        return placedFurnitures;
    }

    public List<GuestBook> getGuestBooks() {
        return guestBooks;
    }

    public List<RoomVisit> getRoomVisits() {
        return roomVisits;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }
}
