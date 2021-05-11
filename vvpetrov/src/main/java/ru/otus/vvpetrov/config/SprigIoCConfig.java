package ru.otus.vvpetrov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.dao.QuestionsDaoCsv;
import ru.otus.vvpetrov.service.StudentService;
import ru.otus.vvpetrov.service.StudentServiceConsole;
import ru.otus.vvpetrov.service.TestOfStudent;
import ru.otus.vvpetrov.service.TestOfStudentImpl;

//Java-based конфигурация контекста Spring
@Component
public class SprigIoCConfig {

    private String file_name;

    public SprigIoCConfig(@Value("${file_name}") String file_name) {
        this.file_name = file_name;
    }

    @Bean
    public QuestionsDao questionsDao() {
        return new QuestionsDaoCsv(file_name);
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

