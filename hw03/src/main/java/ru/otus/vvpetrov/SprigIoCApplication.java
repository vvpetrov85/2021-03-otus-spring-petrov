package ru.otus.vvpetrov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.vvpetrov.service.TestOfStudentService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class SprigIoCApplication {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(SprigIoCApplication.class)
        ) {
            TestOfStudentService service = context.getBean(TestOfStudentService.class);
            service.runStudentTest();
        }
    }
}