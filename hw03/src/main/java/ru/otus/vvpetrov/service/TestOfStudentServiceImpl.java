package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.domain.Student;

@Service
public class TestOfStudentServiceImpl implements TestOfStudentService {
    private final QuestionsDao questionsDao;
    private final StudentService studentService;
    private final IOService ioService;

    int countCorrectAnswer;

    public TestOfStudentServiceImpl(QuestionsDao questionsDao, StudentService studentService, IOService ioService) {
        this.questionsDao = questionsDao;
        this.studentService = studentService;
        this.ioService = ioService;
    }

    @Override
    public void runStudentTest() {
        try {
            //создадим студента
            Student student = studentService.getStudent();
            //Поприветствуем студента и объясним правила ввода ответа
            ioService.outputString(student.toString() + "! Welcome to the student testing!");
            ioService.outputString("You must enter the correct answers as numeric values(for example 1,3)");
            // получим на все вопросы ответы
            questionsDao.getQuestions().forEach(this::askQuestionAndCheckResult);
            // выведем количество правильных ответов
            ioService.outputString("Dear, " + student.toString() + "! Count correct answer = " + countCorrectAnswer);
        } catch (Exception e) {
            ioService.outputString(e.getMessage());
        }
    }

    private Answer getStudentAnswer() {
        Answer studentAnswer = new Answer();
        try {
            studentAnswer.setAnswer(ioService.readIntList());
        } catch (Exception e) {
            ioService.outputString(" Error: " + e.getMessage());
        }
        return studentAnswer;
    }

    private void askQuestionAndCheckResult(Question question) {
        //прогоним его по вопросам
        ioService.outputString(question.getQuestion());
        ioService.outputString(question.getChoiceQuestion());
        //получим ответ
        try {
            Answer arrayIntStudentAnswer = getStudentAnswer();
            //если правильный ответ увеличим показатель на один
            if (arrayIntStudentAnswer.getAnswer().equals(question.getAnswersList().getAnswer())) {
                countCorrectAnswer++;
            }
            // выведем ответ студента и правильный результат
            ioService.outputString("Your answer - " + arrayIntStudentAnswer.getAnswer().toString() +
                    ", correct answer - " + question.getAnswersList().getAnswer().toString());
        } catch (Exception e) {
            // не придумал как проверить в тестах
            ioService.outputString(e.getMessage());
        }
    }

}


