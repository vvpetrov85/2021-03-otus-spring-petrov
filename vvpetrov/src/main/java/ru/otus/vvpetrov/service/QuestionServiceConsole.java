package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Annotation-based конфигурацию контекста
@Service
public class QuestionServiceConsole implements QuestionService {

    @Override
    public Answer getStudentAnswer() {
        Answer studentAnswer = new Answer();
        String answerString = "";
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            answerString = bufferedReader.readLine();
            studentAnswer.setStudentAnswer(getAnswersList(answerString));
        } catch (Exception e) {
            System.out.println("you must enter the correct answers as numeric values(for example 1,3)");
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                answerString = bufferedReader.readLine();
                studentAnswer.setStudentAnswer(getAnswersList(answerString));
            } catch (Exception e1) {
                throw new ExceptionQuestionService(" Error: " + e.getMessage());
            }
        }
        return studentAnswer;
    }

    @Override
    public void printQuestion(String str) {
        System.out.println(str);
    }

    private List<Integer> getAnswersList(String answerString) {
        return Arrays.stream(answerString.trim().split(","))
                .mapToInt(Integer::parseInt).boxed()
                .collect(Collectors.toList());
    }

}

