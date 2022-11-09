package com.sorhive.comprojectserver.member.command.domain.model.member;
import com.sorhive.comprojectserver.member.exception.IdPasswordNotMatchingException;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Collection;
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
 * 2022-11-10       부시연           접속용 방이미지, 방이미지 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_members")
@DynamicInsert
@DynamicUpdate
public class Member implements UserDetails {

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

    @Column(name = "member_offline_image")
    private String offlineRoomImagePath;

    @Column(name = "member_online_image")
    private String onlineRoomImagePath;

    protected Member() { }

    public Member(MemberId memberId, String memberName, String password) {
        setMemberId(memberId);
        setMemberName(memberName);
        setPassword(password);
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }
    @Builder
    public Member(Long memberCode, MemberId memberId, String memberName, String memberEmail, MemberRole memberRole, String password, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn, Collection<? extends GrantedAuthority> authorities) {
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
        this.authorities = authorities;
    }
    public void setOfflineRoomImagePath(String offlineRoomImagePath) { this.offlineRoomImagePath = offlineRoomImagePath; }

    public void setOnlineRoomImagePath(String onlineRoomImagePath) { this.onlineRoomImagePath = onlineRoomImagePath; }

    public String getOfflineRoomImagePath() { return offlineRoomImagePath; }

    public String getOnlineRoomImagePath() { return onlineRoomImagePath; }

    public void setMemberId(MemberId memberId) { this.memberId = memberId; }

    public void setMemberName(String memberName) { this.memberName = memberName; }

    public void setPassword(String password) { this.password = password; }

    public Long getMemberCode() {
        return memberCode;
    }

    public MemberId getMemberId() { return memberId; }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public MemberRole getMemberRole() { return memberRole; }

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

    // 이하 코드는 security 를 위한 용도
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() { return password; }

    @Override
    public String getUsername() {
        return this.memberId.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

}
