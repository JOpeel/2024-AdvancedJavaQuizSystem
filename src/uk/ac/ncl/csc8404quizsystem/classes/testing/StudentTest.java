package uk.ac.ncl.csc8404quizsystem.classes.testing;

import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404quizsystem.classes.QuizSystem;
import uk.ac.ncl.csc8404quizsystem.classes.Student;
import uk.ac.ncl.csc8404quizsystem.factories.question.QuestionFactory;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class for testing methods of student
 */
public class StudentTest {

    Date date = new GregorianCalendar(2002, Calendar.OCTOBER, 28).getTime();

    private Student testStudent = new Student("Jacob", "Peel", date);
    private Student testStudent2 = new Student("Jacob", "Peel", date);

    /**
     * tests getters for student
     */
    @Test
    public void testGetters() {
        assertEquals("Jacob Peel", testStudent.getName());
        assertEquals(date, testStudent.getDob());

        assertEquals("Name: Jacob Peel\nDate of Birth: Mon Oct 28 00:00:00 GMT 2002\nVerdict: TBD\nNumber of Quiz Attempts: 0\nNumber of Revisions: 0\n", testStudent.getStatistics().toString());

    }
}
