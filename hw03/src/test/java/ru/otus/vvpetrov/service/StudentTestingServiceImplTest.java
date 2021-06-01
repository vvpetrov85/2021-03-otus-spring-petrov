package ru.otus.vvpetrov.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.vvpetrov.dao.QuestionsDao;
import ru.otus.vvpetrov.dao.QuestionsDaoCsv;
import ru.otus.vvpetrov.domain.Student;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование студентов")
class StudentTestingServiceImplTest {

    private TestOfStudentService testingService;

    @Mock
    private StudentService studentService;

    @DisplayName("Должен сверить первый ответ...")
    @Test
    void runStudentTestOk() {
        when(studentService.getStudent()).thenReturn(new Student("Petrov", "Vitaly"));
        QuestionsDao questionsDao = new QuestionsDaoCsv("QuestionsTest.csv");
        String msqOut = "1,4,5,6";
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
        IOService ioService = new IOServiceConsole(new PrintStream(baoStream), new ByteArrayInputStream(msqOut.getBytes()));
        testingService = new TestOfStudentServiceImpl(questionsDao, studentService, ioService);
        testingService.runStudentTest();
        assertThat(baoStream.toString()).contains("Count correct answer = 1");
    }

    @DisplayName("Вывод в консоль должен содержать фразу Error during read file...")
    @Test
    void runStudentTestErr() {
        QuestionsDao questionsDao1 = new QuestionsDaoCsv("QuestionsTest1.csv");
        String msg = "Error during read file ";
        when(studentService.getStudent()).thenReturn(new Student("Petrov", "Vitaly"));
        ByteArrayInputStream baiStream = new ByteArrayInputStream(msg.getBytes());
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
        IOService ioService = new IOServiceConsole(new PrintStream(baoStream), baiStream);
        testingService = new TestOfStudentServiceImpl(questionsDao1, studentService, ioService);
        testingService.runStudentTest();
        assertThat(baoStream.toString()).contains(msg);
    }
}
