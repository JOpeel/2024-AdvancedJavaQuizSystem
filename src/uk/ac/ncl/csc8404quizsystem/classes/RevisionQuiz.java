package uk.ac.ncl.csc8404quizsystem.classes;

import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.HashSet;

/**
 * inherits Quiz
 */
public class RevisionQuiz extends Quiz{

    public RevisionQuiz(HashSet<Question> questions){
        super(questions);
    }


}
