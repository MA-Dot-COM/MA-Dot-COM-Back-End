package com.sorhive.comprojectserver.member.command.application.exception;

/**
 * <pre>
 * Class : FailedModifyPasswordException
 * Comment: 비밀번호 수정에 대한 예외처리
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
public class FailedModifyPasswordException extends RuntimeException {
    public FailedModifyPasswordException() {
        super();
    }

    public FailedModifyPasswordException(String message) {
        super(message);
    }

    public FailedModifyPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedModifyPasswordException(Throwable cause) {
        super(cause);
    }

}
