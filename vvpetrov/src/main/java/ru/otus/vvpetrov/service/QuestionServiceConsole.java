package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

import java.util.List;


//Annotation-based конфигурацию контекста
@Service
public class QuestionServiceConsole implements QuestionService {
    IOService ioService = new IOServiceConsole();

    @Override
    public Answer getStudentAnswer() {
        Answer studentAnswer = new Answer();
        String answerString = "";
        try {
            answerString = ioService.inputString();
            List<Integer> answerListInt = ioService.readIntList(answerString);
            studentAnswer.setStudentAnswer(answerListInt);
        } catch (Exception e) {
            throw new ExceptionQuestionService(" Error: " + e.getMessage());
        }
        return studentAnswer;
    }

    @Override
    public void printQuestion(String str) {
        ioService.outputString(str);
    }
}

