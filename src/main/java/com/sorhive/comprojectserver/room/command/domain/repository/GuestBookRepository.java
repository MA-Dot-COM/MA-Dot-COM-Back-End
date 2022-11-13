package com.sorhive.comprojectserver.room.command.domain.repository;

import com.sorhive.comprojectserver.room.command.domain.guestbook.GuestBook;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 * Class : GuestBookRepository
 * Comment: 방명록 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface GuestBookRepository extends Repository<GuestBook, Long> {

    void save(GuestBook guestBook);

}
