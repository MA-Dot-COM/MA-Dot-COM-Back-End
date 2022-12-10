package com.sorhive.comprojectserver.chatting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * Class : NoMongoChattingException
 * Comment: 몽고 채팅이 존재 하지 않을 때 발생하는 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-19       부시연           최초 생성
 * 2022-12-10       부시연           상태값 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "해당 채팅은 존재 하지 않습니다")
public class NoMongoChattingException extends RuntimeException {

    public NoMongoChattingException() {
        super();
    }

    public NoMongoChattingException(String message) {
        super(message);
    }

    public NoMongoChattingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMongoChattingException(Throwable cause) {
        super(cause);
    }

}