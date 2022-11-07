package com.sorhive.comprojectserver.member.command.domain.model.follow;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Follow
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
@Entity
@Table(name = "tbl_follows")
public class Follow {

    @Id
    private Long id;

    @Column(name = "follow_create_time")
    private Timestamp createTime;

    @Column(name = "follow_delete_time")
    private Timestamp deleteTime;

    @Column(name = "follow_delete_yn")
    private Character deleteYn;
}
