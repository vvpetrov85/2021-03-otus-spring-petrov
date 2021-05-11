package ru.otus.vvpetrov.domain;

import java.util.List;

public class Answer {

    private List<Integer> answer;

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setStudentAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
