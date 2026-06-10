package uk.ac.ncl.csc8404quizsystem.interfaces;

/** Question - interface to quiz questions */
public interface Question {

    String getQuestionType();

    String getFormulation();

    Boolean checkAnswer(String answer);


}
