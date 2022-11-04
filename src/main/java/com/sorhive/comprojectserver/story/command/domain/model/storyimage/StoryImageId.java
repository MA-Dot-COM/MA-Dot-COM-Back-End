package com.sorhive.comprojectserver.story.command.domain.model.storyimage;

import com.sorhive.comprojectserver.story.command.domain.model.storycategory.StoryCategoryId;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * Class : StoryImageId
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
public class StoryImageId implements Serializable {

    @Column(name="story_image_id")
    private Long value;

    protected StoryImageId() {
    }

    public StoryImageId(Long value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryImageId that = (StoryImageId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() { return value != null ? value.hashCode() : 0; }

    public static StoryImageId of(Long value) { return new StoryImageId(value); }
}