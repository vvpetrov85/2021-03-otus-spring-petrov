package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.domain.Student;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

@Service
public class StudentTestingServiceImpl implements StudentTestingService {

    private final QuestionsDao questionsDao;
    private final StudentService studentService;
    private int countCorrectAnswer;
    private final IOService ioService;

    public StudentTestingServiceImpl(QuestionsDao questionsDao, StudentService studentService, IOService ioService) {
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
            questionsDao.getQuestions().forEach(this::testingOfStudent);
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

    private void testingOfStudent(Question question) {
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
        } catch (ExceptionQuestionService e) {
            ioService.outputString("Your answer is wrong format. Error :" + e.getMessage());
        }
    }

}


