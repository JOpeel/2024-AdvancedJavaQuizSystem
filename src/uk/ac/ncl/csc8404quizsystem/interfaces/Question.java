package uk.ac.ncl.csc8404quizsystem.interfaces;

/** Question - interface to quiz questions */
public interface Question {

    String getFormulation();

    Boolean checkAnswer(String answer);


}
