package ru.otus.vvpetrov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.dao.QuestionsDaoCsv;
import ru.otus.vvpetrov.service.TestOfStudent;
import ru.otus.vvpetrov.service.TestOfStudentImpl;

//Java-based конфигурация контекста Spring
@Configuration
public class SprigIoCConfig {
    private static final String FILE_NAME = "Questions.csv";

    @Bean
    public QuestionsDao questionsDao() {
        return new QuestionsDaoCsv(FILE_NAME);
    }

    @Bean
    public TestOfStudent questionService(QuestionsDao questionsDao) {
        return new TestOfStudentImpl(questionsDao);
    }

}

