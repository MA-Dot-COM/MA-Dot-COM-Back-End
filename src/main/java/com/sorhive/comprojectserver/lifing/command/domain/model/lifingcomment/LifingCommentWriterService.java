package com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment;

import com.sorhive.comprojectserver.member.command.domain.model.member.MemberCode;

/**
 * <pre>
 * Class : LifingCommentWriterService
 * Comment: 라이핑 댓글 작성자 인터페이스
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
public interface LifingCommentWriterService {
    LifingCommentWriter createLifingCommentWriter(MemberCode lifingCommentWriterMemberCode);
}
