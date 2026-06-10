package uk.ac.ncl.csc8404quizsystem.classes;


import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.HashSet;
import java.util.Iterator;

/**
 * class representing a Quiz
 */
public class Quiz {

    private int quizId;

    private HashSet<Question> questions;

    protected static int quizIdCounter;

    public Quiz(HashSet<Question> questions) {

        this.questions = questions;
        this.quizId = quizIdCounter;
        quizIdCounter++;

    }

    public HashSet<Question> getQuestions(){
        return questions;
    }

    public int getQuizId(){
        return quizId;
    }

    /**
     *
     * @return Quiz as string
     */
    public String toString(){

        String questionsString;
        StringBuilder sb = new StringBuilder();
        Iterator iterator = questions.iterator();

        while(iterator.hasNext()){
            sb.append(iterator.next().toString());
            sb.append("\n\n");
        }

        questionsString = sb.toString();
        return("Quiz ID: " + quizId + "\n" + "Questions: " + questionsString);
    }
}
