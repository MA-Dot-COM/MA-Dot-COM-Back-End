package com.sorhive.comprojectserver.member.command.domain.model.avatarimage;

import javax.persistence.*;

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

}
