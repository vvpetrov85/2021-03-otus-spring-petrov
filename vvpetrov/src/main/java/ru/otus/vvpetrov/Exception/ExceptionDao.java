package ru.otus.vvpetrov.Exception;

public class ExceptionDao extends RuntimeException {
    public ExceptionDao(String message) {
        super(message);
    }
}
