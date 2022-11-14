package com.sorhive.comprojectserver.member.query.avatar;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class : AvatarData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-14       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Entity
@Table(name ="tbl_avatars")
@Getter
public class AvatarData {

    @Id
    @Column(name="avatar_id")
    private Long avatarId;

    @Column(name = "avatar_face_type")
    private Integer faceType;

    @Column(name = "avatar_eyebrows_type")
    private Integer eyeBrowsType;

    @Column(name = "avatar_eye_type")
    private Integer eyeType;

    @Column(name = "avatar_hair_type")
    private Integer hairType;

    protected AvatarData() {}
}
