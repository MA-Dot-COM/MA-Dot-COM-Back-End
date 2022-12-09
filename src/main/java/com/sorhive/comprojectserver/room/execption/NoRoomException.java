package com.sorhive.comprojectserver.room.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : NoRoomException
 * Comment: 방이 없을 때 예외처리
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
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 방은 존재하지 않습니다.")
public class NoRoomException extends RuntimeException {
    public NoRoomException() {
        super();
    }

    public NoRoomException(String message) {
        super(message);
    }

    public NoRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoomException(Throwable cause) {
        super(cause);
    }

}