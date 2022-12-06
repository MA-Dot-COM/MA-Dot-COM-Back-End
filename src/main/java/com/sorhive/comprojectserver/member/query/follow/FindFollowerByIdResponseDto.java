package com.sorhive.comprojectserver.member.query.follow;

import lombok.Getter;

import java.util.List;

/**
 * <pre>
 * Class : FindFollowerByIdResponseDto
 * Comment: 팔로잉 검색 반환 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-12-06       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Getter
public class FindFollowerByIdResponseDto {

    private List<FollowData> followerData;
    public FindFollowerByIdResponseDto(List<FollowData> followData) {
        this.followerData = followData;
    }
}
