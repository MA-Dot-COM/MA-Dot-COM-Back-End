package com.sorhive.comprojectserver.lifing.command.domain.repository;

import com.sorhive.comprojectserver.lifing.command.domain.model.lifingcomment.LifingComment;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : LifingCommentRepository
 * Comment: 라이핑 댓글 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-16       부시연           라이핑 댓글 삭제
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface LifingCommentRepository extends Repository<LifingComment, Long> {

    void save(LifingComment lifingComment);
    LifingComment findByLifingCommentId(Long lifingCommentId);
    LifingComment findByLifingCommentIdAndLifingCommentDeleteYnEquals(Long lifingCommentId, char n);

}
