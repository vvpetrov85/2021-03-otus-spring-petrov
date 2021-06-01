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

    @DisplayName("Должен проверить имя и фамилию студента name name")
    @Test
    void getStudent() {
        Student studentEtalon = new Student("Petrov", "Vitaly");
        when(ioService.inputString()).thenReturn("Vitaly").thenReturn("Petrov");
        studentService = new StudentServiceImpl(ioService);
        Student student = studentService.getStudent();
        assertThat(studentEtalon.getClass()).isEqualTo(student.getClass());
        assertThat(studentEtalon).usingRecursiveComparison().isEqualTo(student);
    }
}