package com.sorhive.comprojectserver.room.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : NoRoomImageException
 * Comment: 방 이미지가 없을 때 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-12-09       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 방 이미지는 존재하지 않습니다")
public class NoRoomImageException extends RuntimeException {
    public NoRoomImageException() {
        super();
    }

    public NoRoomImageException(String message) {
        super(message);
    }

    public NoRoomImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoomImageException(Throwable cause) {
        super(cause);
    }

}