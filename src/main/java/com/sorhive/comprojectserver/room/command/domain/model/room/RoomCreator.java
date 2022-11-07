package com.sorhive.comprojectserver.room.command.domain.model.room;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * <pre>
 * Class : Writer
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-05       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
public class RoomCreator {

    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "room_creator_code"))
    )
    private MemberCode memberCode;

    @Column(name = "room_creator_name")
    private String name;

    protected RoomCreator() {}

    public RoomCreator(MemberCode memberCode, String name) {
        this.memberCode = memberCode;
        this.name = name;
    }

    public MemberCode getMemberCode() {
        return memberCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCreator roomCreator = (RoomCreator) o;
        return Objects.equals(memberCode, roomCreator.memberCode) && Objects.equals(name, roomCreator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberCode, name);
    }
}
