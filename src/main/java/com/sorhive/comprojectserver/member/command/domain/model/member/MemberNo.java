package com.sorhive.comprojectserver.member.command.domain.model.member;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : MemberNo
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
 * @see (참고할 class 또는 외부 url)
 */
@Embeddable
@Access(AccessType.FIELD)
public class MemberNo implements Serializable {

    @Column(name = "member_no")
    private Long value;

    protected MemberNo() {
    }

    public MemberNo(Long value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberNo memberNo = (MemberNo) o;
        return Objects.equals(value, memberNo.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static MemberNo of(Long value) { return new MemberNo(value); }

}