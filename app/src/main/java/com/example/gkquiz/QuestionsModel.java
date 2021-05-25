package com.example.gkquiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class QuestionsModel {
    private String question, correctAnswer;
    private String option1,option2,option3,option4;

    public QuestionsModel(String question, String option1, String option2, String option3, String option4, String correctAnswer) {

        this.question = question;
        this.correctAnswer = correctAnswer;

        ArrayList<String> choice = new ArrayList<>();
        choice.add(option1);
        choice.add(option2);
        choice.add(option3);
        choice.add(option4);
        Collections.shuffle(choice);
        this.option1 = choice.get(0);
        this.option2 = choice.get(1);
        this.option3 = choice.get(2);
        this.option4 = choice.get(3);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }
}