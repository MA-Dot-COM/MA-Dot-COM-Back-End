//package com.sorhive.comprojectserver.lifing.command.domain.model.lifing;
//
//import javax.persistence.AttributeOverride;
//import javax.persistence.AttributeOverrides;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import java.util.Objects;
//
///**
// * <pre>
// * Class : Writer
// * Comment: 클래스에 대한 간단 설명
// * History
// * ================================================================
// * DATE             AUTHOR           NOTE
// * ----------------------------------------------------------------
// * 2022-11-05       부시연           최초 생성
// * </pre>
// *
// * @author 부시연(최초 작성자)
// * @version 1(클래스 버전)
// */
//@Embeddable
//public class LifingWriter {
//
//    @AttributeOverrides(
//            @AttributeOverride(name = "value", column = @Column(name = "lifing_wrtier_code"))
//    )
//    private MemberCode memberCode;
//
//    @Column(name = "lifing_writer_name")
//    private String name;
//
//    protected LifingWriter() {}
//
//    public LifingWriter(MemberCode memberCode, String name) {
//        this.memberCode = memberCode;
//        this.name = name;
//    }
//
//    public MemberCode getMemberCode() {
//        return memberCode;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        LifingWriter lifingWriter = (LifingWriter) o;
//        return Objects.equals(memberCode, lifingWriter.memberCode) && Objects.equals(name, lifingWriter.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(memberCode, name);
//    }
//}
