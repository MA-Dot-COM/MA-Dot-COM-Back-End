package com.sorhive.comprojectserver.member.query.member;

import com.sorhive.comprojectserver.member.query.avatar.AvatarData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Class : FindRandomMemberResponseDto
 * Comment: 랜덤 회원 조회 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
@Setter
public class FindRandomMemberResponseDto {

    private AvatarData ownAvatarData;

    private List<MemberData> memberDtoList;

}
