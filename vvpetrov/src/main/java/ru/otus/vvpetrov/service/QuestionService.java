package ru.otus.vvpetrov.service;

import ru.otus.vvpetrov.domain.Answer;

public interface QuestionService {
    void printQuestion(String str);

    Answer getStudentAnswer();
}
