package ru.otus.vvpetrov.dao;

import ru.otus.vvpetrov.domain.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> getQuestions();
}
