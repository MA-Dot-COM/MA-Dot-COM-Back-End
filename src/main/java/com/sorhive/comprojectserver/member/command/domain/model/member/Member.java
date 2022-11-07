package com.sorhive.comprojectserver.member.command.domain.model.member;
import com.sorhive.comprojectserver.member.exception.IdPasswordNotMatchingException;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "tbl_members")
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;

    @Embedded
    private MemberId memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name ="member_email")
    private String memberEmail;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_MEMBER'")
    private MemberRole memberRole;

    @Column(name = "password")
    @NotBlank(message = "비밀번호를 입력헤주세요")
    private String password;

    @Column(name = "member_create_time")
    private Timestamp createTime;

    @Column(name = "member_upload_time")
    private Timestamp uploadTime;

    @Column(name = "member_delete_time")
    private Timestamp deleteTime;

    @Column(name = "member_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    protected Member() {
    }

    public Member(MemberId memberId, String memberName, String password) {
        setMemberId(memberId);
        setMemberName(memberName);
        setPassword(password);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }

    public Member(Long memberCode, MemberId memberId, String memberName, String memberEmail, MemberRole memberRole, String password, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.password = password;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
    }

    public void setMemberId(MemberId memberId) { this.memberId = memberId; }

    public void setMemberName(String memberName) { this.memberName = memberName; }

    public void setPassword(String password) { this.password = password; }

    public Long getMemberCode() {
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

    public String getPassword() { return password; }

//    public void initializePassword() {
//        String newPassword = generateRandomPassword();
//        this.password = new Password(newPassword);
//    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

//    public void changePassword(String oldPw, String newPw) {
//        if (!password.match(oldPw)) {
//            throw new IdPasswordNotMatchingException();
//        }
//        this.password = new Password(newPw);
//    }

}
