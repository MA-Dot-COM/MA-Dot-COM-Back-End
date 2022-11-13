package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifing.LifingWriterService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : LifingCreatorServiceImpl
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
public class LifingWriterServiceImpl implements LifingWriterService {

    private MemberQueryService memberQueryService;

    public LifingWriterServiceImpl(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @Override
    public LifingWriter createLifingWriter(MemberCode lifingWriterMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(lifingWriterMemberCode.getValue());

        return new LifingWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName(), memberData.getId());
    }

}
