package com.sorhive.comprojectserver.room.command.domain.placedfurniture;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <pre>
 * Class : Angle
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
public class Angle implements Serializable {

    @Column(name = "angle_x")
    private Double angleX;

    @Column(name = "angle_y")
    private Double angleY;

    @Column(name = "angle_z")
    private Double angleZ;

    public Angle() {}

    public Angle(Double angleX, Double angleY, Double angleZ) {
        this.angleX = angleX;
        this.angleY = angleY;
        this.angleZ = angleZ;
    }

    public Double getAngleX() {
        return angleX;
    }

    public Double getAngleY() {
        return angleY;
    }

    public Double getAngleZ() {
        return angleZ;
    }
}
