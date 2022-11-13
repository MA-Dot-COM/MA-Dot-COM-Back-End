package com.sorhive.comprojectserver.config.dto;

import org.springframework.http.HttpStatus;

/**
 * <pre>
 * Class : ApiExceptionDto
 * Comment: : Api 예외처리 전송 객체
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public class ApiExceptionDto {
    private int state;
    private String message;

    public ApiExceptionDto(HttpStatus state, String message){
        this.state = state.value();
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiExceptionDto{" +
                "state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}