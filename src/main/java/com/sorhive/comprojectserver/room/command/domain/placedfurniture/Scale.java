package com.sorhive.comprojectserver.room.command.domain.placedfurniture;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <pre>
 * Class : Scale
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
public class Scale implements Serializable {

    @Column(name = "scale_x")
    private Double scaleX;

    @Column(name = "scale_y")
    private Double scaleY;

    @Column(name = "scale_z")
    private Double scaleZ;

    public Scale() {}

    public Scale(Double scaleX, Double scaleY, Double scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    public Double getScaleX() {
        return scaleX;
    }

    public Double getScaleY() {
        return scaleY;
    }

    public Double getScaleZ() {
        return scaleZ;
    }
}
