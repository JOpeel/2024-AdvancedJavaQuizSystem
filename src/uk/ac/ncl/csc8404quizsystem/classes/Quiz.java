package uk.ac.ncl.csc8404quizsystem.classes;


import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.Iterator;

public class Quiz {



    private int quizId;

    private Question[] questions;

    private static int quizIdCounter;

    public Quiz(Question[] questions) {

        this.questions = questions;
        this.quizId = quizIdCounter;
        quizIdCounter++;

    }

    public Question[] getQuestions(){
        return questions;
    }

    public String toString(){

        String questionsString = "";
        for(int i = 0; i < questions.length; i++){
            questionsString += questions[i].toString();
            questionsString += "\n";
        }

        return("Quiz ID: " + quizId + "\n" + "Questions: " + questionsString);
    }
}
