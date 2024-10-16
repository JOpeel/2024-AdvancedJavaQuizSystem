package uk.ac.ncl.csc8404quizsystem.factories.question;

import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class QuestionFactory implements Question {

    public static final String MULTIPLE_CHOICE = "Multiple Choice";
    public static final String FREE_RESPONSE = "Free Response";

    private static final Map<String, Question> questions = new HashMap<String, Question>();

    private final String questionFormulation;
    private final String answer;



    QuestionFactory(String questionFormulation, String answer){
        answer = answer.toLowerCase();
        answer = answer.trim();
        this.questionFormulation = questionFormulation;
        this.answer = answer;}

    public static Question getInstance(String questionType, String questionFormulation, String answer){

        Question q = questions.get(questionFormulation);

        if(q != null){return q;}

        if (questionType.equals(MULTIPLE_CHOICE)) {
            char[] answers = answer.toCharArray();
            Arrays.sort(answers);
            String processedAnswer = new String(answers);
            q = new MultipleChoiceQuestion(questionFormulation, processedAnswer);


        } else if (questionType.equals(FREE_RESPONSE)) {
            String processedAnswer = answer.trim().replaceAll(" +", " ");
            q = new FreeResponseQuestion(questionFormulation, processedAnswer);
        } else {
            throw new IllegalArgumentException(
                    "invalid question type: " + questionType);
        }

        return q;
    }

    public String getFormulation(){return questionFormulation;}

    public Boolean checkAnswer(String studentAnswer){

        if(this.answer.equals(studentAnswer)){
            return true;
        }
        return false;
    }

    public String toString(){
        return questionFormulation;
    }
}
