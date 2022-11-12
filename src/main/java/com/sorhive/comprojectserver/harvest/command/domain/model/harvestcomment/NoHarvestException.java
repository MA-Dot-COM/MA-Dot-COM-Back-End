package com.sorhive.comprojectserver.harvest.command.domain.model.harvestcomment;

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