package com.sorhive.comprojectserver.feed.command.infra;

import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriter;
import com.sorhive.comprojectserver.feed.command.domain.model.feed.FeedWriterService;
import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;
import com.sorhive.comprojectserver.member.query.member.MemberData;
import com.sorhive.comprojectserver.member.query.member.MemberQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : FeedWriterServiceImpl
 * Comment: 피드 작성자 구현체
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
public class FeedWriterServiceImpl implements FeedWriterService {

    private MemberQueryService memberQueryService;

    /* 피드 작성자 생성 */
    @Override
    public FeedWriter createFeedWriter(MemberCode feedWriterMemberCode) {

        MemberData memberData = memberQueryService.getMemberData(feedWriterMemberCode.getValue());

        return new FeedWriter(MemberCode.of(memberData.getMemberCode()), memberData.getName(), memberData.getId());
    }
}
