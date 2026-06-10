package uk.ac.ncl.csc8404quizsystem.classes.testing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404quizsystem.classes.Quiz;
import uk.ac.ncl.csc8404quizsystem.classes.QuizSystem;
import uk.ac.ncl.csc8404quizsystem.classes.RevisionQuiz;
import uk.ac.ncl.csc8404quizsystem.classes.Student;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class for testing public methods of QuizSystem singleton
 */
public class SystemTest {
    private QuizSystem quizSystem = QuizSystem.getInstance();
    Date date = new GregorianCalendar(2002, Calendar.OCTOBER, 28).getTime();
    Student testStudent = new Student("Jacob", "Peel", date);
    Student testStudent2 = new Student("Joe", "Peel", date);
    Student testStudent3 = new Student("John", "Peel", date);
    String[] answers = {"black","a,c"};
    String[] answers2 = {"black","black"};
    String[] answers3 = {"c","a,c"};
    String[] answers4 = {"a,c","bwah"};

    /**
     * tests generating a quiz
     */
    @Test
    public void testGenerateQuiz() {
        int noOfQuestions = 5;
        Quiz testQuiz2;
        Quiz testQuiz3;
        Quiz testQuiz4;


        testQuiz2 = quizSystem.generateQuiz(noOfQuestions, quizSystem.questionPool);
        assertNotNull(testQuiz2);

        ArrayList<Question> OnlyMCQPool = new ArrayList<>();
        OnlyMCQPool.add(quizSystem.questionPool.get(0));
        OnlyMCQPool.add(quizSystem.questionPool.get(1));
        noOfQuestions = 2;

        testQuiz3 = quizSystem.generateQuiz(noOfQuestions, OnlyMCQPool);
        assertNotNull(testQuiz3);

        ArrayList<Question> OnlyFRQPool = new ArrayList<>();
        OnlyFRQPool.add(quizSystem.questionPool.get(9));
        OnlyFRQPool.add(quizSystem.questionPool.get(10));
        assertNotNull(testQuiz3);
        testQuiz4 = quizSystem.generateQuiz(noOfQuestions, OnlyFRQPool);
        assertNotNull(testQuiz4);
    }

    /**
     * tests generating a revision quiz
     */
    @Test
    public void testRevise() {


        int noOfQuestions = 5;
        RevisionQuiz testQuiz2;
        RevisionQuiz testQuiz3;
        RevisionQuiz testQuiz4;
        RevisionQuiz testQuiz5;


        testQuiz2 = quizSystem.revise(testStudent, noOfQuestions, quizSystem.questionPool);
        assertNotNull(testQuiz2);

        ArrayList<Question> OnlyMCQPool = new ArrayList<>();
        OnlyMCQPool.add(quizSystem.questionPool.get(0));
        OnlyMCQPool.add(quizSystem.questionPool.get(1));
        noOfQuestions = 2;

        testQuiz3 = quizSystem.revise(testStudent, noOfQuestions, OnlyMCQPool);
        assertNotNull(testQuiz3);

        ArrayList<Question> OnlyFRQPool = new ArrayList<>();
        OnlyFRQPool.add(quizSystem.questionPool.get(9));
        OnlyFRQPool.add(quizSystem.questionPool.get(10));
        assertNotNull(testQuiz3);
        testQuiz4 = quizSystem.revise(testStudent, noOfQuestions, OnlyFRQPool);
        assertNotNull(testQuiz4);

        ArrayList<Question> questionsLearntTest = new ArrayList<>();
        questionsLearntTest.add(quizSystem.questionPool.get(0));

        testStudent.getStatistics().addQuestionsLearnt(questionsLearntTest);
        assertThrowsExactly(IllegalArgumentException.class, () -> {quizSystem.revise(testStudent, 2, OnlyMCQPool);});

    }

    /**
     * tests a given student taking a quiz
     */
    @Test
    public void testTakeQuiz() {
        ArrayList<Question> newQuestionPool = new ArrayList<>();
        newQuestionPool.add(quizSystem.questionPool.get(0));
        newQuestionPool.add(quizSystem.questionPool.get(1));
        Quiz newTestQuiz = quizSystem.generateQuiz(2, newQuestionPool);
        assertEquals(0f, quizSystem.takeQuiz(testStudent2, answers2, newTestQuiz));

        //(Because of use of hashSet, cannot predict order of questions when iterating through)
        assertEquals(0.5f, quizSystem.takeQuiz(testStudent2, answers, newTestQuiz));

        assertThrowsExactly(IllegalStateException.class, () -> {quizSystem.takeQuiz(testStudent2, answers3, newTestQuiz);});
    }

    /**
     * tests a given student taking a revision quiz
     */
    @Test
    public void testTakeRevisionQuiz() {
        ArrayList<Question> newQuestionPool = new ArrayList<>();
        newQuestionPool.add(quizSystem.questionPool.get(0));
        newQuestionPool.add(quizSystem.questionPool.get(1));
        RevisionQuiz newTestQuiz = quizSystem.revise(testStudent3, 2, newQuestionPool);
        assertEquals(0f, quizSystem.takeRevisionQuiz(testStudent2, answers2, newTestQuiz));

        //(Because of use of hashSet, cannot predict order of questions when iterating through)
        assertEquals(0.5f, quizSystem.takeQuiz(testStudent2, answers4, newTestQuiz));

        assertThrowsExactly(IllegalStateException.class, () -> {quizSystem.takeQuiz(testStudent2, answers3, newTestQuiz);});
    }


}
