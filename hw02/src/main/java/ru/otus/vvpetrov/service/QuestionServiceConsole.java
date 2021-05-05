package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.StudentAnswer;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

import java.io.*;

//Annotation-based конфигурацию контекста
@Service
public class QuestionServiceConsole implements QuestionService {

    @Override
    public StudentAnswer getStudentAnswer() {

        StudentAnswer studentAnswer = new StudentAnswer();
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        //TODO не могу тут вызвать метод close так как падаест с ошибкой на втором вопросе Stream Closed
        // ну и аналогично try-with-resource
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            studentAnswer.setAnswers(bufferedReader.readLine());

        } catch (Exception e) {
            throw new ExceptionQuestionService(" Error: " + e.getMessage());
        }
        return studentAnswer;
    }

    @Override
    public void printQuestion(String str) {
        System.out.println(str);

    }
}

