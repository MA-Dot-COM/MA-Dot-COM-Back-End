package com.sorhive.comprojectserver.member.command.application.exception;

/**
 * <pre>
 * Class : ExistFollowException
 * Comment: 이미 팔로우를 하는 상황에서 다시 팔로우를 할 때 발생하는 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class ExistFollowException extends RuntimeException {
    public ExistFollowException() {
        super();
    }

    public ExistFollowException(String message) {
        super(message);
    }

    public ExistFollowException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistFollowException(Throwable cause) {
        super(cause);
    }

}
