package ru.otus.vvpetrov.domain;

public class StudentAnswer {

    private String studentAnswer;

    public StudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public StudentAnswer() {
        this.studentAnswer = "";
    }

    public String getAnswers() {
        return studentAnswer;
    }

    public void setAnswers(String studentAnswer) {
        this.studentAnswer = studentAnswer;

    }
}
