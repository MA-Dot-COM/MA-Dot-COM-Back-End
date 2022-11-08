package com.sorhive.comprojectserver.room.command.domain.placedfurniture;

import com.sorhive.comprojectserver.room.command.domain.room.Room;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : PlacedFurniture
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
@Table(name = "tbl_placed_furnitures")
public class PlacedFurniture {

    @Id
    @Column(name="placed_furniture_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name= "category_number")
    private Long categoryNumber;

    @Column(name = "furniture_number")
    private Long furnitureNumber;

    @Column(name = "placed_furniture_create_time")
    private Timestamp createTime;

    @Column(name = "placed_furniture_upload_time")
    private Timestamp uploadTime;

    @Column(name = "placed_furniture_delete_time")
    private Timestamp deleteTime;

    @Column(name = "placed_furniture_delete_yn")
    private Character deleteYn;

    @Embedded
    private Angle angle;

    @Embedded
    private BoxPosition boxPosition;

    @Embedded
    private Position position;

    @Embedded
    private Scale scale;

    public PlacedFurniture() { }

    public PlacedFurniture(Long id, Room room, Long categoryNumber, Long furnitureNumber, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn, Angle angle, BoxPosition boxPosition, Position position, Scale scale) {
        Id = id;
        this.room = room;
        this.categoryNumber = categoryNumber;
        this.furnitureNumber = furnitureNumber;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
        this.angle = angle;
        this.boxPosition = boxPosition;
        this.position = position;
        this.scale = scale;
    }

    public Long getId() {
        return Id;
    }

    public Room getRoom() {
        return room;
    }

    public Long getCategoryNumber() {
        return categoryNumber;
    }

    public Long getFurnitureNumber() {
        return furnitureNumber;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public Character getDeleteYn() {
        return deleteYn;
    }

    public Angle getAngle() {
        return angle;
    }

    public BoxPosition getBoxPosition() {
        return boxPosition;
    }

    public Position getPosition() {
        return position;
    }

    public Scale getScale() {
        return scale;
    }
}
