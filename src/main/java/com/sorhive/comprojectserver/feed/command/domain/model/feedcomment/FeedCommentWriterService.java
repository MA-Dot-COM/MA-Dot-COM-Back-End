package com.sorhive.comprojectserver.feed.command.domain.model.feedcomment;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

/**
 * <pre>
 * Class : FeedCommentWriterService
 * Comment: 피드 댓글 작성자 인터페이스
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
public interface FeedCommentWriterService {
    FeedCommentWriter createFeedCommentWriter(MemberCode feedCommentWriterMemberCode);
}
