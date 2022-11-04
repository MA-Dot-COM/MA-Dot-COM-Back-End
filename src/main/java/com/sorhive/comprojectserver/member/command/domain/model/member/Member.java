package com.sorhive.comprojectserver.member.command.domain.model.member;
import com.sorhive.comprojectserver.member.command.domain.exception.IdPasswordNotMatchingException;

import javax.persistence.*;
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
    private MemberNo no;

    @Embedded
    private MemberId id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Embedded
    private Password password;

    protected Member() {
    }

    public Member(MemberId id, String name, MemberRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public MemberId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberRole getRole() { return role; }

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
