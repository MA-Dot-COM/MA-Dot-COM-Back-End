package com.sorhive.comprojectserver.member.command.application.exception;

/**
 * <pre>
 * Class : SameMemberException
 * Comment: 동일 회원에 대한 예외처리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-13       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class SameMemberException extends RuntimeException{
    public SameMemberException() {
        super();
    }

    public SameMemberException(String message) {
        super(message);
    }

    public SameMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameMemberException(Throwable cause) {
        super(cause);
    }
}
