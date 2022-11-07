package com.sorhive.comprojectserver.member.command.domain.model.avatar;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <pre>
 * Class : Avatar
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name ="tbl_avatars")
@DynamicInsert
@DynamicUpdate
public class Avatar {

    @Id
    @Column(name="avatar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatar_face_type")
    private String faceType;

    @Column(name = "avatar_eyebrows_type")
    private String eyeBrowsType;

    @Column(name = "avatar_eye_type")
    private String eyeType;

    @Column(name = "avatar_hair_type")
    private String hairType;

    protected Avatar() {}

    public Avatar(Long id, String faceType, String eyeBrowsType, String eyeType, String hairType) {
        this.id = id;
        this.faceType = faceType;
        this.eyeBrowsType = eyeBrowsType;
        this.eyeType = eyeType;
        this.hairType = hairType;
    }

    public Long getId() {
        return id;
    }

    public String getFaceType() {
        return faceType;
    }

    public String getEyeBrowsType() {
        return eyeBrowsType;
    }

    public String getEyeType() {
        return eyeType;
    }

    public String getHairType() {
        return hairType;
    }
    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public void setEyeBrowsType(String eyeBrowsType) {
        this.eyeBrowsType = eyeBrowsType;
    }

    public void setEyeType(String eyeType) {
        this.eyeType = eyeType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }
}

