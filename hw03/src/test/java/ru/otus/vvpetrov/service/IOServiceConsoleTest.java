package ru.otus.vvpetrov.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.vvpetrov.exception.ExceptionIOService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование IO потоков")
class IOServiceConsoleTest {
    private ByteArrayOutputStream baoStream;
    private IOService ioService;
    private ByteArrayInputStream baiStream;

    @Mock
    private InputStream inputStream;

    @DisplayName("должен выводить Welcome to the student testing!")
    @Test
    void outputString() {
        String msg = "Welcome to the student testing!";
        baiStream = new ByteArrayInputStream(msg.getBytes());
        baoStream = new ByteArrayOutputStream();
        ioService = new IOServiceConsole(new PrintStream(baoStream), baiStream);
        ioService.outputString(msg);
        assertThat(baoStream.toString()).isEqualTo(msg + "\r\n");
    }

    @DisplayName("должен вернуть Bye from the student testing.")
    @Test
    void inputStringOk() {
        String msg = "Bye from the student testing.";
        baiStream = new ByteArrayInputStream(msg.getBytes());
        ioService = new IOServiceConsole(new PrintStream(new ByteArrayOutputStream()), baiStream);
        String answer = ioService.inputString();
        ioService.outputString(msg);
        assertThat(answer).isEqualTo(msg);
    }

    @DisplayName("должен вернуть ExceptionIOService. Так как в конструкто передали null")
    @Test
    void constructorException() {
        Exception thrown = assertThrows(Exception.class, () -> {
            ioService = new IOServiceConsole(new PrintStream(new ByteArrayOutputStream()), null);
        });
        assertThat(thrown.getClass()).isEqualTo(ExceptionIOService.class);
    }

    @DisplayName("должен вернуть List 1,3")
    @Test
    void readIntListOk() {
        List<Integer> correctListInt = List.of(1, 3);
        String msg = "1,3";
        baiStream = new ByteArrayInputStream(msg.getBytes());
        baoStream = new ByteArrayOutputStream();
        ioService = new IOServiceConsole(new PrintStream(baoStream), baiStream);
        List<Integer> listInt = ioService.readIntList();
        assertEquals(listInt, correctListInt);
    }

    @DisplayName("должен вернуть исключение ")
    @Test
    void readIntListException() {
        ioService = new IOServiceConsole(new PrintStream(new ByteArrayOutputStream()), inputStream);
        assertThatThrownBy(() -> ioService.inputString()).isInstanceOf(ExceptionIOService.class);
    }

    @DisplayName("Не должен выбрасывать исключения ")
    @Test
    void readIntListError() {
        List<Integer> correctList = new LinkedList<>(List.of(1, 4, 7));
        String msg = "1.4.7";
        baiStream = new ByteArrayInputStream(msg.getBytes());
        baoStream = new ByteArrayOutputStream();
        ioService = new IOServiceConsole(new PrintStream(baoStream), baiStream);
        List<Integer> listInt = new LinkedList<>();
        listInt.addAll(ioService.readIntList());
        assertNotEquals(correctList, listInt);
    }
}