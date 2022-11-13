package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.member.query.follow.FollowSummary;
import lombok.Getter;

/**
 * <pre>
 * Class : MemberSummary
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       부시연           최초 생성
 * 2022-11-11       부시연           멤버 조회 전송 객체
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class MemberSummary {

    private Long memberCode;
    private String memberName;
    private String memberId;
    private String avatarImage;
    private String roomImage;
    private Long lifingNo;
    private Character lifingYn;
    private FollowSummary followSummary;

    protected MemberSummary() {}
}
