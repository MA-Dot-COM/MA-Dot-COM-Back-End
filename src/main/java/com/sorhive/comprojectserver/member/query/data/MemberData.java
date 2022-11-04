package com.sorhive.comprojectserver.member.query.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <pre>
 * Class : MemberData
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_member")
public class MemberData {

    @Id
    @Column(name = "member_no")
    private Long no;

    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String name;

    protected MemberData() {
    }

    public MemberData(Long no, String id, String name) {
        this.no = no;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getNo() { return no; }
}
