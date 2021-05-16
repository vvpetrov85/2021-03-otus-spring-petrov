package ru.otus.vvpetrov.SpringIoCApplication;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.vvpetrov.service.QuestionService;

public class SprigIoCApplication {
    private static final String SPRING_CONTEXT = "/spring-context.xml";

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext(SPRING_CONTEXT);
            context.getBean(QuestionService.class).testOfStudent();
            context.close();
        } catch (BeansException e) {
            System.out.println("При работе приложения возникла ошибка. Не найден файл конфигурации " + SPRING_CONTEXT + ". Просьба обратиться к экзаменатору");
        }
        catch (Exception e){
            System.out.println("При работе приложения возникла ошибка - " + e);
        }
    }
}
