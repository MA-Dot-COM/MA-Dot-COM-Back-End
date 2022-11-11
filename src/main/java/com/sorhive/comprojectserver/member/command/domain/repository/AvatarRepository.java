package com.sorhive.comprojectserver.member.command.domain.repository;

import com.sorhive.comprojectserver.member.command.domain.model.avatar.Avatar;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : AvatarRepository
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
public interface AvatarRepository extends Repository<Avatar, Long> {

    void save(Avatar avatar);

}
