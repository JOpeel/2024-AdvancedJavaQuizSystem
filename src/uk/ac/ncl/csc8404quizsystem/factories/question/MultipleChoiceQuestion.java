package uk.ac.ncl.csc8404quizsystem.factories.question;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Class that represents multiple choice questions rather than free response
 */
final class MultipleChoiceQuestion extends QuestionFactory {

    MultipleChoiceQuestion(String questionType, String questionFormulation, String answer){
        super(questionType, questionFormulation, answer);
    }

    /**
     *
     * @param studentAnswer
     * @return true if answer correct once formatted to allow any order of options etc.
     */
    public Boolean checkAnswer(String studentAnswer){
        studentAnswer = studentAnswer.trim();
        studentAnswer = studentAnswer.toLowerCase();

        char[] studentAnswers = studentAnswer.toCharArray();
        Arrays.sort(studentAnswers);
        String processedStudentAnswer = new String(studentAnswers);

        return(super.checkAnswer(processedStudentAnswer));
    }
}
