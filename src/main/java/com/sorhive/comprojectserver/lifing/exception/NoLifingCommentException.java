package com.sorhive.comprojectserver.lifing.exception;
/**
 * <pre>
 * Class : NoLifingCommentException
 * Comment: 해당 라이핑 댓글이 없을 때 예외처리
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
public class NoLifingCommentException extends RuntimeException {

    public NoLifingCommentException() {
        super();
    }

    public NoLifingCommentException(String message) {
        super(message);
    }

    public NoLifingCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLifingCommentException(Throwable cause) {
        super(cause);
    }

}