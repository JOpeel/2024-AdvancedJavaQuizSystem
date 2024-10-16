package uk.ac.ncl.csc8404quizsystem.classes;

import uk.ac.ncl.csc8404quizsystem.factories.question.QuestionFactory;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

public class System {
    private static final System SYSTEM = new System();
    private static Question[] questionPool;

    private System(){
        questionPool[0] = QuestionFactory.getInstance("Multiple Choice", "What colour is a cucumber? a: green, b: blue", "b")
    }

    public static System getInstance(){
        return SYSTEM;
    }




    Quiz generateQuiz(int noOfQuestions){

        Boolean existsFRQ = false;
        Boolean existsMCQ = false;

        for(int i = 0; i < questions.length; i++){
            if(questions[i].getClass().getName() == "FreeResponseQuestion"){
                existsFRQ = true;
            } else if(questions[i].getClass().getName() == "MCQQuestion"){
                existsMCQ = true;
            }
        }

        if(existsFRQ && existsMCQ){
            this.questions = questions;
            this.quizId = quizIdCounter;
            quizIdCounter++;
        } else {
            throw new IllegalArgumentException(
                    "Quiz must contain both multiple choice and free response questions");
        }

        return newQuiz;
    }
    // or QUiz as return type?

    void revise(Student student, int noOfQuestions){}

    void takeQuiz(Student student, String[] answers, Quiz quiz){}

    void takeRevisionQuiz(Student student, String[] answers, RevisionQuiz quiz){}

    void generateStatistics(Student student){}


}
