package com.sorhive.comprojectserver.feed.command.domain.model.feed;

import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedComment;
import com.sorhive.comprojectserver.feed.command.domain.model.feedimage.FeedImage;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : Feed
 * Comment: 피드 도메인 모델
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       부시연           최초 생성
 * 2022-11-12       부시연           총 허니 개수 추가
 * 2022-11-15       부시연           총 허니 개수 null 값 대응
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_feeds")
@Getter
public class Feed {

    @Id
    @Column(name="feed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @Column(name = "feed_content")
    private String feedContent;

    private FeedWriter feedWriter;

    @Column(name = "feed_create_time")
    private Timestamp createTime;

    @Column(name = "feed_upload_time")
    private Timestamp uploadTime;

    @Column(name = "feed_delete_time")
    private Timestamp deleteTime;

    @Column(name = "feed_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    @Column(name = "honey_count")
    @ColumnDefault("0")
    private Integer honeyCount;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    protected List<FeedComment> feedComments = new ArrayList<>();

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    protected List<FeedImage> feedImages = new ArrayList<>();

    protected Feed() { }

    public Feed(FeedWriter feedWriter, String feedContent) {

        this.feedWriter = feedWriter;
        this.feedContent = feedContent;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
        this.deleteYn = 'N';

    }

    /** 허니 총 수 계산 */
    public void setHoneyCount(Integer honeyCount) {
        
        /* 허니 총 수가 null 일 경우 0으로 초기화 */
        if(this.honeyCount == null) {
            this.honeyCount = 0;
        }
        this.honeyCount += honeyCount;
    }
}
