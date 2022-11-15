package com.sorhive.comprojectserver.lifing.command.infra;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriter;
import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingCommentWriterService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : LifingCommentWriterServiceImpl
 * Comment: 라이핑 댓글 작성자 구현체 클래스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-15       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class LifingCommentWriterServiceImpl implements LifingCommentWriterService {

    private MemberQueryService memberQueryService;

    @Override
    public LifingCommentWriter createLifingCommentWriter(MemberCode lifingCommentWriterMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(lifingCommentWriterMemberCode.getValue());

        return new LifingCommentWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName(), memberData.getId());
    }
}
