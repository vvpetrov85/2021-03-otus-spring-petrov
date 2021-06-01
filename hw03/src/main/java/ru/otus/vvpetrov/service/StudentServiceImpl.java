package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    public StudentServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student getStudent() {
        ioService.outputString("enter name");
        String name = ioService.inputString();
        ioService.outputString("enter surname");
        String surName = ioService.inputString();

        return new Student(surName, name);
    }

}
