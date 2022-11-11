package com.sorhive.comprojectserver.member.query.member;

import lombok.Getter;

/**
 * <pre>
 * Class : FollowingMemberSummary
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class FollowingMemberSummary {
    private Long followId;
    private Long followingId;

    protected FollowingMemberSummary() {}
}
