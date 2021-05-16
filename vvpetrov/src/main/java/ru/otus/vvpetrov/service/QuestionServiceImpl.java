package ru.otus.vvpetrov.service;

import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Question;

import java.util.Arrays;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionsDao questionsDao;

    public QuestionServiceImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public void testOfStudent() {
        try {
            questionsDao.getQuestions()
                    .stream()
                    .forEach(question -> printQuestionAndGetAnswer(question)
                    );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printQuestionAndGetAnswer(Question question) {
        System.out.println(question.getQuestion());
        System.out.println("Choice: " + question.getChoiceQuestion());
        // здесь необходми вызов метода по получению ответа

        System.out.println("Correct Answer: " + Arrays.toString(question.getAnswers()));
    }
}


