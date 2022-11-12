package com.sorhive.comprojectserver.member.command.application.exception;

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
