package com.sorhive.comprojectserver.member.query.spec;

import com.sorhive.comprojectserver.member.query.data.MemberData;
import org.springframework.data.jpa.domain.Specification;

public class MemberDataSpecs {

    public static Specification<MemberData> nameLike(String keyword) {
        return (root, query, cb) -> cb.like(root.<String>get("name"), keyword + "%");
    }
}
