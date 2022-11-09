package com.sorhive.comprojectserver.room.command.domain.room;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.roomvisit.RoomVisit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
 * 2022-11-08       부시연           최초 생성
 * 2022-11-09       부시연           몽고 DB 도메인으로 변경
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_rooms")
public class Room {

    @Id
    @Column(name="room_id")
    private Long id;

    private RoomCreator roomCreator;

    @Column(name="room_no")
    private String roomNo;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<GuestBook> guestBooks = new ArrayList<GuestBook>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomVisit> roomVisits = new ArrayList<RoomVisit>();

    protected Room() { }

    public Room(Long id, String roomNo, RoomCreator roomCreator) {
        setId(id);
        setRoomNo(roomNo);
        setRoomCreator(roomCreator);
    }

}