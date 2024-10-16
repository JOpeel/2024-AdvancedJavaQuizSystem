package uk.ac.ncl.csc8404quizsystem.factories.question;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

final class FreeResponseQuestion extends QuestionFactory {

    FreeResponseQuestion(String questionFormulation, String answer){
        super(questionFormulation, answer);
    }

    public Boolean checkAnswer(String studentAnswer){
        studentAnswer = studentAnswer.toLowerCase();
        studentAnswer = studentAnswer.trim();
        return(super.checkAnswer(studentAnswer));
    }
}
