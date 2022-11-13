package com.sorhive.comprojectserver.member.query.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * Class : MemberMapper
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           자신을 포함한 전체 회원 조회
 * 2022-11-11       부시연           제일 높은 회원 코드값 조회
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Mapper
public interface MemberMapper {
    MemberSummary findAllByMemberCode(Long memberCode);

    int findMaxMemberCode();

    List<MemberSummary> findAllFollowerByMemberCode(Long memberCode, long offset);
}
