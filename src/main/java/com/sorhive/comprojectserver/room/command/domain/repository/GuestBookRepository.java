package com.sorhive.comprojectserver.room.command.domain.repository;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : GuestBookRepository
 * Comment: 방명록 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * 2022-11-13       부시연           save 추가
 * 2022-11-16       부시연           findById 추가
 * 2022-11-16       부시연           findByIdAndDeleteYnEquals 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface GuestBookRepository extends Repository<GuestBook, Long> {

    void save(GuestBook guestBook);

    GuestBook findById(Long guestBookId);
    Optional<GuestBook> findByIdAndDeleteYnEquals(Long guestBookId, char n);
}
