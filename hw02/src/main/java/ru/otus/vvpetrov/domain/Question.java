package ru.otus.vvpetrov.domain;

public class Question {
    private final Integer numberQuestion;
    private final String question;
    private final String choiceQuestion;
    private final Answer answersList;

    public Question(Integer numberQuestion, String question, String choiceQuestion, Answer answerList) {
        this.numberQuestion = numberQuestion;
        this.question = question;
        this.choiceQuestion = choiceQuestion;
        this.answersList = answerList;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoiceQuestion() {
        return choiceQuestion;
    }

    public Answer getAnswersList() {
        return answersList;
    }
}
