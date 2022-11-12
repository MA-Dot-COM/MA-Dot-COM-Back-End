package com.sorhive.comprojectserver.harvest.command.domain.model.honey;

import com.sorhive.comprojectserver.harvest.command.domain.model.harvest.HarvestId;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : LikedFeed
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
@Table(name = "tbl_honey")
@Getter
public class Honey {

    @Id
    @Column(name="honey_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "honey_create_time")
    private Timestamp createTime;

    @Column(name = "honey_delete_time")
    private Timestamp deleteTime;

    @Column(name = "honey_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    @Embedded
    private HarvestId harvestId;

    @Embedded
    private MemberCode memberCode;

    protected Honey() {}

    public Honey(MemberCode memberCode, HarvestId harvestId) {
        this.harvestId = harvestId;
        this.memberCode = memberCode;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.deleteYn = 'N';
    }

    public void setDeleteYn(Character deleteYn) {
        this.deleteYn = deleteYn;
        this.deleteTime = new Timestamp(System.currentTimeMillis());
    }
}
