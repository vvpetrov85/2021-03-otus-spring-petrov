package ru.otus.vvpetrov.exception;

public class ExceptionStudentService extends RuntimeException {
    public ExceptionStudentService(String message, Throwable cause) {
        super(message, cause);
    }
}
