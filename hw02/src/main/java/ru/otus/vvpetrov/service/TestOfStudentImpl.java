package ru.otus.vvpetrov.service;

import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.domain.Student;

import java.util.Arrays;

public class TestOfStudentImpl implements TestOfStudent {

    private final QuestionsDao questionsDao;
    private int i;
    private Student student = new Student();

    public TestOfStudentImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public void testOfStudent() {
        try {
            //создаим студента
            student = getStudentFIO();
            // получим на все вопросы ответы
            questionsDao.getQuestions()
                    .stream()
                    .forEach(question -> resultOfQuestion(question)
                    );
            // выведем количество правильных ответов
            System.out.println("Dear, " + student.toString() + "! Count correct answer = " + i);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Student getStudentFIO() {
        QuestionService questionService = new QuestionServiceConsole();
        questionService.printQuestion("enter surname");
        student.setSurName(questionService.getStudentAnswer().getAnswers());
        questionService.printQuestion("enter name");
        student.setName(questionService.getStudentAnswer().getAnswers());
        return student;
    }

    private void resultOfQuestion(Question question) {
        QuestionService questionService = new QuestionServiceConsole();
        //прогоним его по вопросам
        questionService.printQuestion(question.getQuestion());
        questionService.printQuestion(question.getChoiceQuestion());
        //получим ответ
        int[] arrayIntStudentAnswer = new int[10];
        try {
            arrayIntStudentAnswer = Arrays.stream(questionService
                    .getStudentAnswer().getAnswers().trim().split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("you must enter the correct answers as numeric values(for example 1,3)");
            try {
                arrayIntStudentAnswer = Arrays.stream(questionService
                        .getStudentAnswer().getAnswers().trim().split(",")).mapToInt(Integer::parseInt).toArray();
            } catch (Exception e1) {
                System.out.println(" Error: " + e1.getMessage());
            }
        } finally {
            if (Arrays.equals(arrayIntStudentAnswer, question.getAnswers())) {
                i++;
            }
            System.out.println("Your answer - " + Arrays.toString(arrayIntStudentAnswer) +
                    ", correct answer - " + Arrays.toString(question.getAnswers()));
        }
    }
}


