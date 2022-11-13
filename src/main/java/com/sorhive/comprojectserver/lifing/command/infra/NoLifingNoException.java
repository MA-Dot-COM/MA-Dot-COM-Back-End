package com.sorhive.comprojectserver.lifing.command.infra;

public class NoLifingNoException extends RuntimeException{
    public NoLifingNoException() {
        super();
    }

    public NoLifingNoException(String message) {
        super(message);
    }

    public NoLifingNoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLifingNoException(Throwable cause) {
        super(cause);
    }
}
