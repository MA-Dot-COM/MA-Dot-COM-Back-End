package com.sorhive.comprojectserver.harvest.command.application.exception;
/**
 * <pre>
 * Class : NoHarvestException
 * Comment: 클래스에 대한 간단 설명
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
public class NoHarvestException extends RuntimeException {
    public NoHarvestException() {
        super();
    }

    public NoHarvestException(String message) {
        super(message);
    }

    public NoHarvestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHarvestException(Throwable cause) {
        super(cause);
    }

}