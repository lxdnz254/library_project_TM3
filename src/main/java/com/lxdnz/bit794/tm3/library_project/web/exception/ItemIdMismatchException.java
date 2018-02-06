package com.lxdnz.bit794.tm3.library_project.web.exception;

public class ItemIdMismatchException extends RuntimeException {

    public ItemIdMismatchException() {
        super();
    }

    public ItemIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ItemIdMismatchException(final String message) {
        super(message);
    }

    public ItemIdMismatchException(final Throwable cause) {
        super(cause);
    }
}
