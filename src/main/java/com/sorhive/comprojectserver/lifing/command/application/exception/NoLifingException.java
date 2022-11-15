package com.sorhive.comprojectserver.lifing.command.application.exception;
/**
 * <pre>
 * Class : NoLifingException
 * Comment: 해당 라이핑이 없을 때 발생하는 예외처리
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
public class NoLifingException extends RuntimeException {
    public NoLifingException() {
        super();
    }

    public NoLifingException(String message) {
        super(message);
    }

    public NoLifingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLifingException(Throwable cause) {
        super(cause);
    }

}