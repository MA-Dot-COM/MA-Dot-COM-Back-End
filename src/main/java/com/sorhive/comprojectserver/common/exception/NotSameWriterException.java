package com.sorhive.comprojectserver.common.exception;
/**
 * <pre>
 * Class : NotSameWriterException
 * Comment: 작성자와 요청자가 다를 때 예외처리
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
public class NotSameWriterException extends RuntimeException {
    public NotSameWriterException() {
        super();
    }

    public NotSameWriterException(String message) {
        super(message);
    }

    public NotSameWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSameWriterException(Throwable cause) {
        super(cause);
    }

}