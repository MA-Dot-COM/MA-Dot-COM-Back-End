package com.sorhive.comprojectserver.member.command.application.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : AvatarImageDto
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
public class AvatarImageDto {

    private MultipartFile avatarImage;

    public AvatarImageDto(MultipartFile avatarImage) {
        this.avatarImage = avatarImage;
    }
    public MultipartFile getAvatarImage() { return avatarImage; }
}
