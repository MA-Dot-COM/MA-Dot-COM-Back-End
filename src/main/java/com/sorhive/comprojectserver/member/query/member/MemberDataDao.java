package com.sorhive.comprojectserver.member.query.member;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <pre>
 * Class : MemberDataDAO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * 2022-11-12       부시연           회원 아이디 검색 시 자기 자신 제외 기능 추가
 * 2022-11-12       부시연           자신을 제외한 전체 회원 목록 조회
 * 2022-11-13       부시연           자신을 제외한 멤버 코드 조회
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface MemberDataDao extends Repository<MemberData, String> {

    MemberData findById(String memberId);

    List<MemberData> findAllByMemberCodeIsNot(Long memberCode);

    List<MemberData> findByIdLikeAndMemberCodeIsNot(String memberId, Long memberCode);

    MemberData findByMemberCode(Long memberCode);

    MemberData findByMemberCodeAndMemberCodeIsNot(Long memberCode, Long temp);
}
