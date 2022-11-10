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
public class NoFollowException extends RuntimeException {
    public NoFollowException() {
        super();
    }

    public NoFollowException(String message) {
        super(message);
    }

    public NoFollowException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFollowException(Throwable cause) {
        super(cause);
    }

}
