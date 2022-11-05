package com.sorhive.comprojectserver.room.command.domain.model.roomvisit;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @EmbeddedId
    private RoomVisitId id;

    @Column(name = "room_visit_time")
    private Timestamp time;

    protected RoomVisit() {}
    public RoomVisit(RoomVisitId id, Timestamp time) {
        this.id = id;
        this.time = time;
    }

    public RoomVisitId getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }
}
