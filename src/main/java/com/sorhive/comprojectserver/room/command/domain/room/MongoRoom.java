package com.sorhive.comprojectserver.room.command.domain.room;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import com.sorhive.comprojectserver.room.command.domain.roomvisit.RoomVisit;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Document(collection = "room")
public class MongoRoom {
    @Id
    private String id;
    private RoomCreator roomCreator;
    private List<Map<String,Object>> furnitures;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<GuestBook> guestBooks = new ArrayList<GuestBook>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomVisit> roomVisits = new ArrayList<RoomVisit>();

    public MongoRoom(RoomCreator roomCreator, List<Map<String, Object>> furnitures) {
        setRoomCreator(roomCreator);
        setFurnitures(furnitures);
    }

    public void setFurnitures(List<Map<String, Object>> furnitures) {
        this.furnitures = furnitures;
    }

    public void setRoomCreator(RoomCreator roomCreator) {
        this.roomCreator = roomCreator;
    }
}
