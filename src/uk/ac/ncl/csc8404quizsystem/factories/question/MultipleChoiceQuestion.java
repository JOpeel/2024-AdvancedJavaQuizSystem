package uk.ac.ncl.csc8404quizsystem.factories.question;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

final class MultipleChoiceQuestion extends QuestionFactory {

    MultipleChoiceQuestion(String questionFormulation, String answer){
        super(questionFormulation, answer);
    }

    public Boolean checkAnswer(String studentAnswer){
        studentAnswer = studentAnswer.trim();
        studentAnswer = studentAnswer.toLowerCase();

        char[] studentAnswers = studentAnswer.toCharArray();
        Arrays.sort(studentAnswers);
        String processedStudentAnswer = new String(studentAnswers);

        return(super.checkAnswer(processedStudentAnswer));
    }
}
