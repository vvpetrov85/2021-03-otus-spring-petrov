package ru.otus.vvpetrov.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.vvpetrov.domain.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Получение информации о студете")
class StudentServiceImplTest {

    private StudentService studentService;

    @Mock
    private IOServiceConsole ioService;

    @DisplayName("Должен вернуть студента name name")
    @Test
    void getStudent() {
        Student studentEtalon = new Student("name", "name");
        when(ioService.inputString()).thenReturn("name");
        studentService = new StudentServiceImpl(ioService);
        Student student = studentService.getStudent();
        assertThat(studentEtalon.toString()).isEqualTo(student.toString());
    }
}