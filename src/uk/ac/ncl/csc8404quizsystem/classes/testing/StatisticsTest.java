package uk.ac.ncl.csc8404quizsystem.classes.testing;

import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404quizsystem.classes.*;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing methods of Statistics
 */
public class StatisticsTest {

    Date date = new GregorianCalendar(2002, Calendar.OCTOBER, 28).getTime();
    private Student testStudent = new Student("Jacob", "Peel", date);
    RevisionQuiz testRevisionQuiz = new RevisionQuiz(QuizSystem.getInstance().testQuiz.getQuestions());

    /**
     *Test method for getters of statistics
     */
    @Test
    public void testGetters(){
        assertEquals(0, testStudent.getStatistics().getNoOfQuizAttempts());
        assertEquals(0, testStudent.getStatistics().getNoOfQuizRevisions());
        assertEquals("Name: Jacob Peel\nDate of Birth: Mon Oct 28 00:00:00 GMT 2002", testStudent.getStatistics().getStudentInfo());

    }

    /**
     * test method for incrementing attempts of quiz
     */
    @Test
    public void incrementQuizAttemptsTest() {
        testStudent.getStatistics().incrementQuizAttempts();
        assertEquals(true, (testStudent.getStatistics().getNoOfQuizAttempts() == 1));
        testStudent.getStatistics().incrementQuizAttempts();
        assertEquals(true, (testStudent.getStatistics().getNoOfQuizAttempts() == 2));
        assertEquals(true, testStudent.getStatistics().getVerdict() == Statistics.verdict.FAIL);
    }

    /**
     * test method for incrementing revisions
     */
    @Test
    public void incrementRevisionQuizAttemptsTest() {
        testStudent.getStatistics().incrementRevisionQuizAttempts();
        assertEquals(true, (testStudent.getStatistics().getNoOfQuizRevisions() == 1));
    }

    /**
     * test method for setting verdict enum
     */
    @Test
    public void setVerdictTest() {
        assertEquals(Statistics.verdict.TBD, testStudent.getStatistics().getVerdict());

        testStudent.getStatistics().setVerdict(Statistics.verdict.PASS);

        assertEquals(Statistics.verdict.PASS, testStudent.getStatistics().getVerdict());

    }

    /**
     * test method for adding questions learnt to students statistics
     */
    @Test
    public void testAddQuestionsLearnt() {
        ArrayList<Question> testQuestions = new ArrayList<>();
        testQuestions.add(QuizSystem.getInstance().questionPool.get(0));
        testQuestions.add(QuizSystem.getInstance().questionPool.get(1));

        testStudent.getStatistics().addQuestionsLearnt(testQuestions);

        assertEquals(testStudent.getStatistics().getQuestionsLearnt(), testQuestions);

    }

    /**
     * test method for adding quiz completed to students statistics
     */
    @Test
    public void testAddQuizCompleted() {
        testStudent.getStatistics().addQuizCompleted(QuizSystem.getInstance().testQuiz, 0.5f);

        assertTrue(testStudent.getStatistics().getQuizzesCompleted().contains(QuizSystem.getInstance().testQuiz));
        assertEquals(0.5f, testStudent.getStatistics().getQuizScore(QuizSystem.getInstance().testQuiz.getQuizId()));
    }

    /**
     * test method for adding completed revision quiz
     */
    @Test
    public void testAddRevisionQuizCompleted(){
        RevisionQuiz testRevisionQuiz = new RevisionQuiz(QuizSystem.getInstance().testQuiz.getQuestions());

        System.out.println(QuizSystem.getInstance().testQuiz.getQuizId());
        System.out.println(testRevisionQuiz.getQuizId());

        testStudent.getStatistics().addRevisionCompleted(testRevisionQuiz, 0.5f);
        assertTrue(testStudent.getStatistics().getRevisionCompleted().contains(testRevisionQuiz));
        assertEquals(0.5f, testStudent.getStatistics().getRevisionScore(testRevisionQuiz.getQuizId()));
    }

    /**
     * test method for printing quiz and score from students statistics
     */
    @Test
    public void testPrintQuizAndScore(){
        testStudent.getStatistics().addQuizCompleted(QuizSystem.getInstance().testQuiz, 0.5f);
        testStudent.getStatistics().addRevisionCompleted(testRevisionQuiz, 0.2f);

        assertEquals("Quiz ID: 0 Score: 0.5\nRevision Quiz ID: 1 Score: 0.2\n", testStudent.getStatistics().printQuizAndScore());
    }



}
