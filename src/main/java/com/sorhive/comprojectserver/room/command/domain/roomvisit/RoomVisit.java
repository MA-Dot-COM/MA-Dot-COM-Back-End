package com.sorhive.comprojectserver.room.command.domain.roomvisit;

import com.sorhive.comprojectserver.room.command.domain.room.Room;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : RoomVisit
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
@Table(name = "tbl_room_visits")
public class RoomVisit {

    @Id
    @Column(name="room_visit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_visit_time")
    private Timestamp time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private RoomVisitor roomVisitor;

    protected RoomVisit() {}
    public RoomVisit(Long id, Timestamp time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }
}
