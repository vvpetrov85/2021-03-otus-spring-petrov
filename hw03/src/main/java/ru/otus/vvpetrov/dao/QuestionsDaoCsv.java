package ru.otus.vvpetrov.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.vvpetrov.domain.Answer;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.exception.ExceptionDao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//Репозиторий - это место, где хранятся данные. Сервис - это то, что манипулирует данными.
@Repository
public class QuestionsDaoCsv implements QuestionsDao {
    private final String fileQuestions;

    public QuestionsDaoCsv(@Value("${file_name}") String fileQuestions) {
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
                        answerForQuestion.setAnswer(
                                Arrays.stream(q[3].trim().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
                        listQuestion.add(new Question(
                                Integer.valueOf(q[0]),
                                q[1].trim(),
                                q[2].trim(),
                                answerForQuestion));
                    }
            );
        } catch (Exception e) {
            throw new ExceptionDao("Error during read file " + fileQuestions, e);
        }
        return listQuestion;
    }
}
