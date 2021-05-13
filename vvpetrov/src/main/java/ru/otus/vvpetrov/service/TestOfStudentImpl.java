package ru.otus.vvpetrov.service;

import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.domain.Student;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

public class TestOfStudentImpl implements TestOfStudent {

    private final QuestionsDao questionsDao;
    private final StudentService studentService;
    private int countCorrectAnswer;

    public TestOfStudentImpl(QuestionsDao questionsDao, StudentService studentService) {
        this.questionsDao = questionsDao;
        this.studentService = studentService;
    }

    @Override
    public void testOfStudent() {
        try {
            //создадим студента
            Student student = studentService.getStudent();
            //Поприветствуем студента и объясним правила ввода ответа
            System.out.println(student.toString() + "! Welcome to the student testing!");
            System.out.println("You must enter the correct answers as numeric values(for example 1,3)");
            // получим на все вопросы ответы
            questionsDao.getQuestions().forEach(this::resultOfQuestion);
            // выведем количество правильных ответов
            System.out.println("Dear, " + student.toString() + "! Count correct answer = " + countCorrectAnswer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void resultOfQuestion(Question question) {
        QuestionService questionService = new QuestionServiceConsole();
        //прогоним его по вопросам
        questionService.printQuestion(question.getQuestion());
        questionService.printQuestion(question.getChoiceQuestion());
        //получим ответ
        try {
            Answer arrayIntStudentAnswer = questionService.getStudentAnswer();
            //если правильный ответ увеличим показатель на один
            if (arrayIntStudentAnswer.getAnswer().equals(question.getAnswersList().getAnswer())) {
                countCorrectAnswer++;
            }
            // выведем ответ студента и правильный результат
            System.out.println("Your answer - " + arrayIntStudentAnswer.getAnswer().toString() +
                    ", correct answer - " + question.getAnswersList().getAnswer().toString());
        } catch (ExceptionQuestionService e) {
            System.out.println("Your answer is wrong format. Error :" + e.getMessage());
        }
    }
}


