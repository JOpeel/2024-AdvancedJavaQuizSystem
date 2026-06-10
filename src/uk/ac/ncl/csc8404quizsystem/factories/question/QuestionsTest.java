package uk.ac.ncl.csc8404quizsystem.factories.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uk.ac.ncl.csc8404quizsystem.classes.QuizSystem;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

/**
 * Class that tests methods of Questions
 */
public class QuestionsTest {

    /**
     * tests that the factory pattern works
     */
    @Test
    public final void testFactory(){

        Question testQuestion = QuestionFactory.getInstance("Multiple Choice", "What colour is a zebra? a: black, b: yellow, c: white", "a,c");
        assertNotNull(testQuestion);
        System.out.println(testQuestion.toString());


        assertNotNull(QuizSystem.getInstance().questionPool.get(1));
        assertInstanceOf(MultipleChoiceQuestion.class, QuizSystem.getInstance().questionPool.get(1));

        assertNotNull(QuizSystem.getInstance().questionPool.get(8));
        assertInstanceOf(FreeResponseQuestion.class, QuizSystem.getInstance().questionPool.get(8));

        assertThrowsExactly(IllegalArgumentException.class, () -> {final Question question3 = QuestionFactory.getInstance("test", "test?", "test");});
    }

    /**
     * tests Question getters
     */
    @Test
    public final void testGetters(){
        assertEquals("Multiple Choice", QuizSystem.getInstance().questionPool.get(1).getQuestionType());
        assertEquals("What colour is an orange? a: green, b: blue, c: orange", QuizSystem.getInstance().questionPool.get(1).getFormulation());
    }

    /**
     * tests if checkAnswer works for different question types
     */
    @Test
    public final void testCheckAnswer(){

        //multiple choice with 1 answer
        assertEquals(true, QuizSystem.getInstance().questionPool.get(1).checkAnswer("c"));
        assertEquals(false, QuizSystem.getInstance().questionPool.get(1).checkAnswer("a"));

        assertEquals(true, QuizSystem.getInstance().questionPool.get(1).checkAnswer(" c"));
        assertEquals(true, QuizSystem.getInstance().questionPool.get(1).checkAnswer("C"));

        assertEquals(false, QuizSystem.getInstance().questionPool.get(1).checkAnswer("ac"));

        //multiple choice with multiple answers
        assertEquals(true, QuizSystem.getInstance().questionPool.get(3).checkAnswer("a,d"));
        assertEquals(false, QuizSystem.getInstance().questionPool.get(3).checkAnswer("b"));

        assertEquals(true, QuizSystem.getInstance().questionPool.get(3).checkAnswer(" a,d"));
        assertEquals(true, QuizSystem.getInstance().questionPool.get(3).checkAnswer("a,D"));

        assertEquals(false, QuizSystem.getInstance().questionPool.get(3).checkAnswer("a,d,b"));

        assertEquals(true, QuizSystem.getInstance().questionPool.get(3).checkAnswer("d,a"));

        //Free response questions

        assertEquals(true, QuizSystem.getInstance().questionPool.get(8).checkAnswer("green"));
        assertEquals(false, QuizSystem.getInstance().questionPool.get(8).checkAnswer("blue"));

        assertEquals(true, QuizSystem.getInstance().questionPool.get(8).checkAnswer(" green"));
        assertEquals(true, QuizSystem.getInstance().questionPool.get(8).checkAnswer("GReen"));
        assertEquals(true, QuizSystem.getInstance().questionPool.get(15).checkAnswer("bright   blue"));

        assertEquals(false, QuizSystem.getInstance().questionPool.get(8).checkAnswer("greenblue"));

    }
}
