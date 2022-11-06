package com.sorhive.comprojectserver.member.command.domain.model.member;
import com.sorhive.comprojectserver.member.exception.IdPasswordNotMatchingException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Random;

/**
 * <pre>
 * Class : IdPasswordNotMatchingException
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
public class Member {

    @EmbeddedId
    private MemberCode memberCode;

    @Embedded
    private MemberId memberId;

    @Column(name = "member_name")
    private String memberName;

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
    private Character deleteYn;

    protected Member() {
    }

    public Member(MemberCode memberCode, MemberId memberId, String memberName, String memberEmail, MemberRole memberRole, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
    }

    public MemberCode getMemberCode() {
        return memberCode;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public MemberRole getMemberRole() {
        return memberRole;
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

    public Password getPassword() { return password; }

    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changePassword(String oldPw, String newPw) {
        if (!password.match(oldPw)) {
            throw new IdPasswordNotMatchingException();
        }
        this.password = new Password(newPw);
    }

}
