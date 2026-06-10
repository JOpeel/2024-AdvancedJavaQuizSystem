package uk.ac.ncl.csc8404quizsystem.factories.question;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

/**
 * Class that represents free response questions rather than multiple choice
 */
final class FreeResponseQuestion extends QuestionFactory {

    FreeResponseQuestion(String questionType, String questionFormulation, String answer){
        super(questionType, questionFormulation, answer);
    }

    /**
     *
     * @param studentAnswer
     * @return true if answer is correct once reformatted to allow non-case-sensitivity etc.
     */
    public Boolean checkAnswer(String studentAnswer){
        studentAnswer = studentAnswer.toLowerCase();
        studentAnswer = studentAnswer.trim();
        studentAnswer = studentAnswer.trim().replaceAll(" +", " ");
        return(super.checkAnswer(studentAnswer));
    }
}
