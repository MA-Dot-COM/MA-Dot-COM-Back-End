package com.sorhive.comprojectserver.feed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : NoRequiredFeedModifyException
 * Comment: 피드 수정에 필요한 것이 없을 때 발생하는 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-20       부시연           최초 생성
 * 2022-12-15       부시연           예외처리 상태값 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 수정 요청에는 필요한 정보가 없습니다")
public class NoRequiredFeedModifyException extends RuntimeException {

    public NoRequiredFeedModifyException() {
        super();
    }

    public NoRequiredFeedModifyException(String message) {
        super(message);
    }

    public NoRequiredFeedModifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRequiredFeedModifyException(Throwable cause) {
        super(cause);
    }

}

