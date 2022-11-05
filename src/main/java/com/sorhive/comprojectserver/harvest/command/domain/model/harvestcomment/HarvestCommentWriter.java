package com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment;

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
public class HarvestCommentWriter {

    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "harvest_comment_wrtier_code"))
    )
    private MemberCode memberCode;

    @Column(name = "harvest_writer_name")
    private String name;

    protected HarvestCommentWriter() {}

    public HarvestCommentWriter(MemberCode memberCode, String name) {
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
        HarvestCommentWriter harvestCommentWriter = (HarvestCommentWriter) o;
        return Objects.equals(memberCode, harvestCommentWriter.memberCode) && Objects.equals(name, harvestCommentWriter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberCode, name);
    }
}
