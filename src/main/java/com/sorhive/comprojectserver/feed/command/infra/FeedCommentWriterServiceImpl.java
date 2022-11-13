package com.sorhive.comprojectserver.feed.command.infra;

import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feedcomment.FeedCommentWriterService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : FeedCommentWriterServiceImpl
 * Comment: 피드 댓글 작성자 구현체 클래스
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
@AllArgsConstructor
public class FeedCommentWriterServiceImpl implements FeedCommentWriterService {

    private MemberQueryService memberQueryService;

    @Override
    public FeedCommentWriter createFeedCommentWriter(MemberCode feedCommentWriterMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(feedCommentWriterMemberCode.getValue());

        return new FeedCommentWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName(), memberData.getId());
    }
}
