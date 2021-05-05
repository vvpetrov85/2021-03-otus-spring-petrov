package ru.otus.vvpetrov.service;

import ru.otus.vvpetrov.domain.StudentAnswer;

public interface QuestionService {
    StudentAnswer getStudentAnswer();
    void printQuestion(String str);
}
