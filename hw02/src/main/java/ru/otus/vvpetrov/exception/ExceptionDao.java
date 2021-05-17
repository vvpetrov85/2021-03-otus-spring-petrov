package ru.otus.vvpetrov.exception;

public class ExceptionDao extends RuntimeException {
    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }
}
