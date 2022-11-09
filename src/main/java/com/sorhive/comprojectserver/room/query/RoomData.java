package com.sorhive.comprojectserver.room.query;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.roomvisit.RoomVisit;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : RoomData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_rooms")
@Getter
public class RoomData {

    @Id
    @Column(name="room_id")
    private Long id;

    @Column(name = "room_creator_code")
    private Long code;

    @Column(name = "room_creator_name")
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<GuestBook> guestBooks = new ArrayList<GuestBook>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomVisit> roomVisits = new ArrayList<RoomVisit>();
}
