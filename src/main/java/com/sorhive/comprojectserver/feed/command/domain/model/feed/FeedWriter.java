package com.sorhive.comprojectserver.feed.command.domain.model.feed;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * <pre>
 * Class : FeedWriter
 * Comment: 피드 작성자 도메인 모델
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
public class FeedWriter {

    @AttributeOverrides(
            @AttributeOverride(name = "value", column = @Column(name = "feed_writer_code"))
    )
    private MemberCode memberCode;

    @Column(name = "feed_writer_name")
    private String name;

    protected FeedWriter() {}

    public FeedWriter(MemberCode memberCode, String name) {
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
        FeedWriter feedWriter = (FeedWriter) o;
        return Objects.equals(memberCode, feedWriter.memberCode) && Objects.equals(name, feedWriter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberCode, name);
    }
}
