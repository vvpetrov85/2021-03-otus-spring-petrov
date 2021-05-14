package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.domain.Student;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

@Service
public class TestOfStudentImpl implements TestOfStudent {

    private final QuestionsDao questionsDao;
    private final StudentService studentService;
    private static int countCorrectAnswer;
    private final IOService ioService;
    private final QuestionService questionService;

    public TestOfStudentImpl(QuestionsDao questionsDao, StudentService studentService, IOService ioService, QuestionService questionService) {
        this.questionsDao = questionsDao;
        this.studentService = studentService;
        this.ioService = ioService;
        this.questionService = questionService;
    }

    @Override
    public void testOfStudent() {
        try {
            //создадим студента
            Student student = studentService.getStudent();
            //Поприветствуем студента и объясним правила ввода ответа
            ioService.outputString(student.toString() + "! Welcome to the student testing!");
            ioService.outputString("You must enter the correct answers as numeric values(for example 1,3)");
            // получим на все вопросы ответы
            questionsDao.getQuestions().forEach(this::resultOfQuestion);
            // выведем количество правильных ответов
            ioService.outputString("Dear, " + student.toString() + "! Count correct answer = " + countCorrectAnswer);
        } catch (Exception e) {
            ioService.outputString(e.getMessage());
        }
    }

    private void resultOfQuestion(Question question) {
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
            ioService.outputString("Your answer - " + arrayIntStudentAnswer.getAnswer().toString() +
                    ", correct answer - " + question.getAnswersList().getAnswer().toString());
        } catch (ExceptionQuestionService e) {
            ioService.outputString("Your answer is wrong format. Error :" + e.getMessage());
        }
    }
}


