package ru.otus.vvpetrov.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.vvpetrov.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование загрузки файла csv")
class QuestionsDaoCsvTest {

    private QuestionsDaoCsv questionsDaoCsv;

    @Test
    @DisplayName("Тестирование Dao")
    void getQuestions() throws IOException, CsvException {
        questionsDaoCsv = new QuestionsDaoCsv("QuestionsTest.csv");
        List<Question> questionList = questionsDaoCsv.getQuestions();
        assertThat(questionList).isNotNull();

    }
}