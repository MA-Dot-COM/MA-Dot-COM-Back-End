package com.sorhive.comprojectserver.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : AlreadyDeleteException
 * Comment: 이미 삭제 하고 있는 상황에 대한 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-16       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 요청은 이미 삭제되었습니다.")
public class AlreadyDeleteException extends RuntimeException {
    public AlreadyDeleteException() {
        super();
    }

    public AlreadyDeleteException(String message) {
        super(message);
    }

    public AlreadyDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDeleteException(Throwable cause) {
        super(cause);
    }

}