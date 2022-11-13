package com.sorhive.comprojectserver.lifing.command.domain.model.lifing;//package com.sorhive.comprojectserver.lifing.command.domain.model.lifing;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
@AllArgsConstructor
@Getter
public class LifingWriter {

    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "lifing_writer_code"))
    )
    private MemberCode memberCode;

    @Column(name = "lifing_writer_name")
    private String name;

    @Column(name = "lifing_writer_id")
    private String id;

    protected LifingWriter() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifingWriter that = (LifingWriter) o;
        return Objects.equals(memberCode, that.memberCode) && Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberCode, name, id);
    }
}
