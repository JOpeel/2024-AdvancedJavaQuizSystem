package uk.ac.ncl.csc8404quizsystem.factories.question;

import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * factory class which implements question interface
 */
public abstract class QuestionFactory implements Question {

    private static final Map<String, Question> questions = new HashMap<String, Question>();

    private final String questionFormulation;
    private final String answer;
    private final String questionType;



    QuestionFactory(String questionType, String questionFormulation, String answer){
        answer = answer.toLowerCase();
        answer = answer.trim();
        this.questionFormulation = questionFormulation;
        this.answer = answer;
        this.questionType = questionType;}

    /**
     *
     * @param questionType
     * @param questionFormulation
     * @param answer
     * @return Question, ensuring no duplicates and correct question types
     */
    public static Question getInstance(String questionType, String questionFormulation, String answer){

        Question q = questions.get(questionFormulation);

        if(q != null){return q;}

        if (questionType.equals("Multiple Choice")) {
            char[] answers = answer.toCharArray();
            Arrays.sort(answers);
            String processedAnswer = new String(answers);
            q = new MultipleChoiceQuestion(questionType, questionFormulation, processedAnswer);


        } else if (questionType.equals("Free Response")) {
            String processedAnswer = answer.trim().replaceAll(" +", " ");
            q = new FreeResponseQuestion(questionType, questionFormulation, processedAnswer);
        } else {
            throw new IllegalArgumentException(
                    "invalid question type: " + questionType);
        }

        return q;
    }

    /**
     *
     * @return the questions formulation i.e. the question itself and options for MCQ
     */
    public String getFormulation(){return questionFormulation;}

    /**
     *
     * @return type of question (free response or multiple choice)
     */
    public String getQuestionType(){return questionType;}

    /**
     *
     * @param studentAnswer
     * @return true if argument answer same as this.answer
     */
    public Boolean checkAnswer(String studentAnswer){

        if(this.answer.equals(studentAnswer)){
            return true;
        }
        return false;
    }

    /**
     *
     * @return overrides toString
     */
    public String toString(){
        return ("Question type: " + questionType + "\n" + "Question: " + questionFormulation);
    }
}
