package com.sorhive.comprojectserver.member.command.domain.repository;

import com.sorhive.comprojectserver.member.command.domain.model.member.Member;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : MemberRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface MemberRepository extends Repository<Member, MemberId> {

    Optional<Member> findById(MemberId memberId);

    void save(Member member);

    Optional<Member> findByMemberId(MemberCode memberCode);
}
