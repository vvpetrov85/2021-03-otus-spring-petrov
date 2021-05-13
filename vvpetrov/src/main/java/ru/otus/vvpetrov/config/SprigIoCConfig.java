package ru.otus.vvpetrov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.dao.QuestionsDaoCsv;
import ru.otus.vvpetrov.service.StudentService;
import ru.otus.vvpetrov.service.StudentServiceConsole;
import ru.otus.vvpetrov.service.TestOfStudent;
import ru.otus.vvpetrov.service.TestOfStudentImpl;

//Java-based конфигурация контекста Spring
@Configuration
public class SprigIoCConfig {

    @Bean
    public QuestionsDao questionsDao(@Value("${file_name}") String fileName) {
        return new QuestionsDaoCsv(fileName);
    }

    @Bean
    public StudentService studentService() {
        return new StudentServiceConsole();
    }

    @Bean
    public TestOfStudent questionService(QuestionsDao questionsDao, StudentService studentService) {
        return new TestOfStudentImpl(questionsDao, studentService);
    }

}

