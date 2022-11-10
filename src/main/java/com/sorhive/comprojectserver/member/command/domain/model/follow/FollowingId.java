package com.sorhive.comprojectserver.member.command.domain.model.follow;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : FollowId
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
@Embeddable
@Access(AccessType.FIELD)
public class FollowingId implements Serializable {

    @Column(name = "following_id")
    private Long value;

    protected FollowingId() {
    }

    public FollowingId(Long value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingId followerMember = (FollowingId) o;
        return Objects.equals(value, followerMember.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static FollowingId of(Long value) { return new FollowingId(value); }
}
