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
    @DisplayName("Тестирование Dao: список вопросов должен быть не пустой, список должен содержать класс Question и первый вопрос не должен быть null")
    void getQuestionsOk() {
        questionsDaoCsv = new QuestionsDaoCsv("QuestionsTest.csv");
        List<Question> questionList = questionsDaoCsv.getQuestions();

        assertThat(questionList).isNotNull();
        assertThat(questionList.get(0).getClass()).isEqualTo(Question.class);
        assertThat(questionList.get(0).getQuestion()).isNotNull();
        assertThat(questionList.get(0).getQuestion()).isEqualTo("Which four can be thrown using the throw statement?");
    }

    @Test
    @DisplayName("Тестирование Dao. Получаем исключение.")
    void getQuestionsError() {

        questionsDaoCsv = new QuestionsDaoCsv("QuestionsTest1.csv");

        Exception thrown = assertThrows(Exception.class, () -> {
            questionsDaoCsv.getQuestions();
        });
        assertThat(thrown.getClass()).isEqualTo(ExceptionDao.class);
    }
}