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
    @Column(name="placed_furniture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name= "category_number")
    private Long categoryNumber;

    @Column(name = "furniture_number")
    private Long furnitureNumber;

    @Column(name = "position_x")
    private Double positionX;

    @Column(name = "position_y")
    private Double positionY;

    @Column(name = "position_z")
    private Double positionZ;

    @Column(name = "scale_x")
    private Double scaleX;

    @Column(name = "scale_y")
    private Double scaleY;

    @Column(name = "scale_z")
    private Double scaleZ;

    @Column(name = "angle_x")
    private Double angleX;

    @Column(name = "angle_y")
    private Double angleY;

    @Column(name = "angle_z")
    private Double angleZ;

    @Column(name = "box_position_x")
    private Double boxPositionX;

    @Column(name = "box_position_y")
    private Double boxPositionY;

    @Column(name = "box_position_z")
    private Double boxPositionZ;

    @Column(name = "placed_furniture_create_time")
    private Timestamp createTime;

    @Column(name = "placed_furniture_upload_time")
    private Timestamp uploadTime;

    @Column(name = "placed_furniture_delete_time")
    private Timestamp deleteTime;

    @Column(name = "placed_furniture_delete_yn")
    private Character deleteYn;
}
