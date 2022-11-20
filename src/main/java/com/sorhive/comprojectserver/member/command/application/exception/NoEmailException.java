package com.sorhive.comprojectserver.member.command.application.exception;

/**
 * <pre>
 * Class : NoEmailException
 * Comment: 해당 이메일이 없을 때 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * 2022-11-10       부시연           메시지 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class NoEmailException extends RuntimeException {
    public NoEmailException() {
        super();
    }

    public NoEmailException(String message) {
        super(message);
    }

    public NoEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmailException(Throwable cause) {
        super(cause);
    }

}
