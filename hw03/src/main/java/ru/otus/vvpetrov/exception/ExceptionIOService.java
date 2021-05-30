package ru.otus.vvpetrov.exception;

public class ExceptionIOService extends RuntimeException {
    public ExceptionIOService(String message, Throwable cause) {
        super(message, cause);
    }
}
