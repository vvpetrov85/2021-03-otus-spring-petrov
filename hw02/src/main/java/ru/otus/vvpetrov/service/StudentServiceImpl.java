package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.Student;
import ru.otus.vvpetrov.exception.ExceptionStudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    public StudentServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student getStudent() {
        Student student = new Student();
        try {
            ioService.outputString("enter name");
            student.setName(ioService.inputString());
            ioService.outputString("enter surname");
            student.setSurName(ioService.inputString());
        } catch (Exception e) {
            throw new ExceptionStudentService(" Error during get information of student: ", e);
        }
        return student;
    }

}
