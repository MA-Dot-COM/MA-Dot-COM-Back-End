package com.sorhive.comprojectserver.story.command.domain.model.story;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : StoryId
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
@Access(AccessType.FIELD)
public class StoryId implements Serializable {

    @Column(name="story_id")
    private Long value;

    protected StoryId() {
    }

    public StoryId(Long value){ this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryId storyId = (StoryId) o;
        return Objects.equals(value, storyId.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static StoryId of(Long value) { return new StoryId(value); }

}