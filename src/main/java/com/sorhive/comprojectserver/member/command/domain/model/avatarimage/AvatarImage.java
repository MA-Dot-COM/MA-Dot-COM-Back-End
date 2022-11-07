package com.sorhive.comprojectserver.member.command.domain.model.avatarimage;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : AvatarImage
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_avatar_images")
@DynamicInsert
@DynamicUpdate
public class AvatarImage {

    @Id
    @Column(name = "avatar_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatar_image_path")
    private String path;

    @Column(name = "avatar_original_name")
    private String orginalName;

    @Column(name = "avatar_saved_name")
    private String savedName;

    @Column(name = "avatar_upload_time")
    private Timestamp uploadTime;

    public AvatarImage() {
    }

    public AvatarImage(String path, String orginalName, String savedName) {
        setPath(path);
        setOrginalName(orginalName);
        setSavedName(savedName);
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }
    public AvatarImage(Long id, String path, String orginalName, String savedName) {
        this.id = id;
        this.path = path;
        this.orginalName = orginalName;
        this.savedName = savedName;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getOrginalName() {
        return orginalName;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setOrginalName(String orginalName) {
        this.orginalName = orginalName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }
}
