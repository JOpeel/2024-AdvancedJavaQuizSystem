package uk.ac.ncl.csc8404quizsystem.factories.question;

public class MultipleChoiceQuestion extends QuestionFactory {

    MultipleChoiceQuestion(String questionFormulation, String answer){
        //alphabetise answer with commas in place, make sure answer has 2-4 options
        super(questionFormulation, answer);
    }

    public Boolean checkAnswer(String answer){
        answer = answer.toLowerCase();
        answer = answer.trim();
        //Find letters in String and alphabetise with commas in place
        //to both input answer and answer checked against

    }
}
