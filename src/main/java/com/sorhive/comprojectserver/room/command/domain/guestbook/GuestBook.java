package com.sorhive.comprojectserver.room.command.domain.guestbook;

import com.sorhive.comprojectserver.room.command.domain.placedfurniture.Angle;
import com.sorhive.comprojectserver.room.command.domain.placedfurniture.BoxPosition;
import com.sorhive.comprojectserver.room.command.domain.placedfurniture.Position;
import com.sorhive.comprojectserver.room.command.domain.placedfurniture.Scale;
import com.sorhive.comprojectserver.room.command.domain.room.Room;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : GuestBook
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
@Table(name = "tbl_guestbooks")
public class GuestBook {

    @Id
    @Column(name="guestbook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guestbook_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private GuestBookWriter guestBookWriter;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "angleX", column = @Column(name = "guestbook_angle_x")),
            @AttributeOverride(name = "angleY", column = @Column(name = "guestbook_angle_y")),
            @AttributeOverride(name = "angleZ", column = @Column(name = "guestbook_angle_z"))
    })
    private Angle angle;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "boxPositionX", column = @Column(name = "guestbook_box_position_x")),
            @AttributeOverride(name = "boxPositionY", column = @Column(name = "guestbook_box_position_y")),
            @AttributeOverride(name = "boxPositionZ", column = @Column(name = "guestbook_box_position_z"))
    })
    private BoxPosition boxPosition;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "positionX", column = @Column(name = "guestbook_position_x")),
            @AttributeOverride(name = "positionY", column = @Column(name = "guestbook_position_y")),
            @AttributeOverride(name = "positionZ", column = @Column(name = "guestbook_position_z"))
    })
    private Position position;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "scaleX", column = @Column(name = "guestbook_scale_x")),
            @AttributeOverride(name = "scaleY", column = @Column(name = "guestbook_scale_y")),
            @AttributeOverride(name = "scaleZ", column = @Column(name = "guestbook_scale_z"))
    })
    private Scale scale;

    @Column(name = "guestbook_create_time")
    private Timestamp createTime;

    @Column(name = "guestbook_upload_time")
    private Timestamp uploadTime;

    @Column(name = "guestbook_delete_time")
    private Timestamp deleteTime;

    @Column(name = "guestbook_delete_yn")
    private Character deleteYn;

    protected GuestBook () {}

    public GuestBook(Long id, String content, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
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
}