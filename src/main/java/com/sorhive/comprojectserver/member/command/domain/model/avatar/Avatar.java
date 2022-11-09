package com.sorhive.comprojectserver.member.command.domain.model.avatar;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Long id;

    @Column(name = "avatar_face_type")
    private String faceType;

    @Column(name = "avatar_eyebrows_type")
    private String eyeBrowsType;

    @Column(name = "avatar_eye_type")
    private String eyeType;

    @Column(name = "avatar_hair_type")
    private String hairType;

    @Column(name = "avatar_create_time")
    private Timestamp createTime;

    @Column(name = "avatar_upload_time")
    private Timestamp uploadTime;

    @Column(name = "avatar_delete_time")
    private Timestamp deleteTime;

    @Column(name = "avatar_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    protected Avatar() {}

    public Avatar(Long id, String faceType, String eyeBrowsType, String eyeType, String hairType) {
        setId(id);
        setEyeBrowsType(eyeBrowsType);
        setEyeType(eyeType);
        setFaceType(faceType);
        setHairType(hairType);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

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

