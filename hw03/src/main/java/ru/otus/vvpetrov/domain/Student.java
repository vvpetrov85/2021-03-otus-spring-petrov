package ru.otus.vvpetrov.domain;

public class Student {
    private String surName;
    private String name;

    public Student(String surName, String name) {
        this.surName = surName;
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getSurName() + " " + this.getName();
    }
}

