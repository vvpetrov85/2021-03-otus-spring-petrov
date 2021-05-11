package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.Student;
import ru.otus.vvpetrov.exception.ExceptionQuestionService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class StudentServiceConsole implements StudentService {

    @Override
    public Student getStudent() {
        Student student = new Student();
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("enter name");
            student.setName(bufferedReader.readLine());
            System.out.println("enter surname");
            student.setSurName(bufferedReader.readLine());
        } catch (Exception e) {
            throw new ExceptionQuestionService(" Error: " + e.getMessage());
        }
        return student;
    }

}
