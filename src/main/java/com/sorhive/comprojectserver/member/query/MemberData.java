package com.sorhive.comprojectserver.member.query;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberRole;
import com.sorhive.comprojectserver.member.command.domain.model.member.Password;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

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
@Table(name = "tbl_members")
public class MemberData {

    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "member_id")
    private String id;

    @Column(name = "member_name")
    private String name;

    @Column(name ="member_email")
    private String memberEmail;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Embedded
    private Password password;

    @Column(name = "member_create_time")
    private Timestamp createTime;

    @Column(name = "member_upload_time")
    private Timestamp uploadTime;

    @Column(name = "member_delete_time")
    private Timestamp deleteTime;

    @Column(name = "member_delete_yn")
    @ColumnDefault("N")
    private Character deleteYn;

    protected MemberData() {
    }

    public MemberData(Long code, String id, String name, String memberEmail, MemberRole memberRole, Password password, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn) {
        this.code = code;
        this.id = id;
        this.name = name;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.password = password;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
    }

    public Long getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public Password getPassword() {
        return password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public Character getDeleteYn() {
        return deleteYn;
    }

}
