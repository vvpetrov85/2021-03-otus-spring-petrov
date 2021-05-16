package ru.otus.vvpetrov.domain;

public class Question {
    private final Integer numberQuestion;
    private final String question;
    private final String choiceQuestion;
    private final int[] answers;

    public Question(Integer numberQuestion, String question, String choiceQuestion, int[] answers) {
        this.numberQuestion = numberQuestion;
        this.question = question;
        this.choiceQuestion = choiceQuestion;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoiceQuestion() {
        return choiceQuestion;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public int[] getAnswers() {
        return answers;
    }
}
