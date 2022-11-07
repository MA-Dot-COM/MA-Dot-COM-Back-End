package com.sorhive.comprojectserver.member.command.domain.model.avatar;

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

}

