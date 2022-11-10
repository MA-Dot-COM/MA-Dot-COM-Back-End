package com.sorhive.comprojectserver.member.command.domain.model.follow;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Follow
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_follows")
public class Follow {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FollowerMember followerMember;

    @Embedded
    private FollowingMember followingMember;

    @Column(name = "follow_create_time")
    private Timestamp createTime;

    @Column(name = "follow_delete_time")
    private Timestamp deleteTime;

    @Column(name = "follow_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    public Follow(FollowerMember followerMember, FollowingMember followingMember) {

        setFollowerMember(followerMember);
        setFollowingMember(followingMember);
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() { return id; }

    public void setFollowerMember(FollowerMember followerMember) { this.followerMember = followerMember; }

    public void setFollowingMember(FollowingMember followingMember) { this.followingMember = followingMember; }
}
