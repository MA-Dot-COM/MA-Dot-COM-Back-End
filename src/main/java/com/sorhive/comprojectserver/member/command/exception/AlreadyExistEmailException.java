package com.sorhive.comprojectserver.member.command.exception;

/**
 * <pre>
 * Class : AlreadyExistEmailException
 * Comment: 이미 이메일이 존재할 때 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-17       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class AlreadyExistEmailException extends RuntimeException{
    public AlreadyExistEmailException() {
        super();
    }

    public AlreadyExistEmailException(String message) {
        super(message);
    }

    public AlreadyExistEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistEmailException(Throwable cause) {
        super(cause);
    }
}
