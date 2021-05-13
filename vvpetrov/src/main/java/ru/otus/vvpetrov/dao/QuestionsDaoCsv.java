package ru.otus.vvpetrov.dao;

import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.exception.ExceptionDao;
import ru.otus.vvpetrov.domain.Question;
import com.opencsv.*;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionsDaoCsv implements QuestionsDao {
    private final String fileQuestions;

    public QuestionsDaoCsv(String fileQuestions) {
        this.fileQuestions = fileQuestions;
    }

    @Override
    public List<Question> getQuestions() {
        final CSVParser parser;
        List<Question> listQuestion = new LinkedList<>();

        parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        try (InputStream questionInputStream = this.getClass().getClassLoader().getResourceAsStream(fileQuestions);
             Reader reader = new InputStreamReader(questionInputStream);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1)
                     .withCSVParser(parser)
                     .build();
        ) {
            new LinkedList<>(csvReader.readAll()).forEach(
                    (q) -> {
                        Answer answerForQuestion = new Answer();
                        answerForQuestion.setStudentAnswer(
                                Arrays.stream(q[3].trim().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
                        listQuestion.add(new Question(
                                Integer.valueOf(q[0]),
                                q[1].trim(),
                                q[2].trim(),
                                answerForQuestion));
                    }
            );
        } catch (Exception e) {
            throw new ExceptionDao(fileQuestions + " Error: " + e.getMessage());
        }
        return listQuestion;
    }
}
