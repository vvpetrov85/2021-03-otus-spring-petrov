package ru.otus.vvpetrov.domain;

public class Student {
    private String surName;
    private String name;

    public Student(String surName, String name) {
        this.surName = surName;
        this.name = name;
    }

    public Student() {
        this.surName = "";
        this.name = "";
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSurName() {
        return surName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return this.getSurName() + " " + this.getName();
    }
}

