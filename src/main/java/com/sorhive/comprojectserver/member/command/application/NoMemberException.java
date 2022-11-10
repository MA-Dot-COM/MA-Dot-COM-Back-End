package com.sorhive.comprojectserver.member.command.application;

/**
 * <pre>
 * Class : IdPasswordNotMatchingException
 * Comment: 클래스에 대한 간단 설명
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
public class NoMemberException extends RuntimeException {
    public NoMemberException() {
        super();
    }

    public NoMemberException(String message) {
        super(message);
    }

    public NoMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMemberException(Throwable cause) {
        super(cause);
    }

}
