package com.sorhive.comprojectserver.member.query.follow;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class : FollowData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-10       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Entity
@Table(name = "tbl_follows")
@Getter
@NoArgsConstructor
public class FollowData {

    @Id
    @Column(name = "follow_id")
    private Long id;

    @Column(name = "follower_id")
    private Long followerId;

    @Column(name = "following_id")
    private Long followingId;

    @Column(name = "follow_delete_yn")
    private Character followDeleteYn;

}
