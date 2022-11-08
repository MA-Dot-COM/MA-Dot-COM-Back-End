package com.sorhive.comprojectserver.room.command.domain.guestbook;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <pre>
 * Class : BoxPosition
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-08       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Embeddable
@Access(AccessType.FIELD)
public class BoxPosition implements Serializable {
    @Column(name = "box_position_x")
    private Double boxPositionX;

    @Column(name = "box_position_y")
    private Double boxPositionY;

    @Column(name = "box_position_z")
    private Double boxPositionZ;

    public BoxPosition() {}

    public BoxPosition(Double boxPositionX, Double boxPositionY, Double boxPositionZ) {
        this.boxPositionX = boxPositionX;
        this.boxPositionY = boxPositionY;
        this.boxPositionZ = boxPositionZ;
    }

    public Double getBoxPositionX() {
        return boxPositionX;
    }

    public Double getBoxPositionY() {
        return boxPositionY;
    }

    public Double getBoxPositionZ() {
        return boxPositionZ;
    }
}
