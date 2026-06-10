package uk.ac.ncl.csc8404quizsystem.classes.testing;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404quizsystem.classes.*;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Quiz class
 */
public class QuizTest {

    /**
     * test method for get methods of Quiz
     */
    @Test
    public void testQuizGetters(){
        assertEquals(0, QuizSystem.getInstance().testQuiz.getQuizId());
        assertEquals(QuizSystem.getInstance().testQuizQuestions, QuizSystem.getInstance().testQuiz.getQuestions());

    }

    /**
     * Tests overriding of toString of Quiz
     */
    @Test
    public void testToString(){
        System.out.println(QuizSystem.getInstance().testQuiz.toString());

        assertEquals("Quiz ID: 0\n" +
                "Questions: Question type: Multiple Choice\n" +
                "Question: What colour is a kiwi? a: green, b: blue, c: orange\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "Question: What colour is an orange? a: green, b: blue, c: orange\n" +
                "\n" +
                "Question type: Multiple Choice\n" +
                "Question: What colour is a zebra? a: black, b: yellow, c: white\n" +
                "\n" +
                "Question type: Free Response\n" +
                "Question: What colour is a flamingo?\n" +
                "\n" +
                "Question type: Free Response\n" +
                "Question: What colour is a gherkin?\n\n", QuizSystem.getInstance().testQuiz.toString());

    }
}
