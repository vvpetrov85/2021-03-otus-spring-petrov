package ru.otus.vvpetrov.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.vvpetrov.domain.Question;
import ru.otus.vvpetrov.exception.ExceptionDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование загрузки файла csv")
class QuestionsDaoCsvTest {

    private QuestionsDaoCsv questionsDaoCsv;

    @Test
    @DisplayName("Тестирование Dao")
    void getQuestionsOk() {
        questionsDaoCsv = new QuestionsDaoCsv("QuestionsTest.csv");
        List<Question> questionList = questionsDaoCsv.getQuestions();
        assertThat(questionList).isNotNull();

    }

    @Test
    @DisplayName("Тестирование Dao. Получаем исключение.")
    void getQuestionsError(){

        questionsDaoCsv = new QuestionsDaoCsv("QuestionsTest1.csv");

        Exception thrown = assertThrows(Exception.class, () -> {
            questionsDaoCsv.getQuestions();
        });
        assertThat(thrown.getClass()).isEqualTo(ExceptionDao.class);
    }
}