package com.sorhive.comprojectserver.lifing.command.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : AlreadyHoneyException
 * Comment: 이미 허니 하고 있는 상황에 대한 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-15       부시연           라이핑으로 이동
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "이미 허니를 했습니다.")
public class AlreadyHoneyException extends RuntimeException {
    public AlreadyHoneyException() {
        super();
    }

    public AlreadyHoneyException(String message) {
        super(message);
    }

    public AlreadyHoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyHoneyException(Throwable cause) {
        super(cause);
    }

}