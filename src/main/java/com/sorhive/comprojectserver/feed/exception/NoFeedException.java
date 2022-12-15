package com.sorhive.comprojectserver.feed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : NoFeedException
 * Comment: 해당 피드가 없을 때 발생하는 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-12-15       부시연           예외처리 상태값 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 피드는 존재하지 않습니다")
public class NoFeedException extends RuntimeException {
    public NoFeedException() {
        super();
    }

    public NoFeedException(String message) {
        super(message);
    }

    public NoFeedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFeedException(Throwable cause) {
        super(cause);
    }

}