package ru.otus.vvpetrov.exception;

public class ExceptionQuestionService extends RuntimeException {
    public ExceptionQuestionService(String message, Throwable cause) {
        super(message, cause);
    }
}
