package ru.otus.vvpetrov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.vvpetrov.service.TestOfStudent;

@Configuration
@ComponentScan
public class SprigIoCApplication {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(SprigIoCApplication.class);) {

            TestOfStudent service = context.getBean(TestOfStudent.class);

            service.testOfStudent();
        }
    }
}