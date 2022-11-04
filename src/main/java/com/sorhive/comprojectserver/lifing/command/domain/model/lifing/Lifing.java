package com.sorhive.comprojectserver.lifing.command.domain.model.lifing;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Story
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
@Entity
@Table(name = "tbl_lifing")
public class Lifing {

    @EmbeddedId
    private LifingId id;

    @Column(name = "lifing_create_time")
    private Timestamp createTime;

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_time")
    private Timestamp deleteTime;

    @Column(name = "lifing_delete_yn")
    private Character deleteYn;
}
