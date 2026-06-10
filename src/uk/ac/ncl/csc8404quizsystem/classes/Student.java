package uk.ac.ncl.csc8404quizsystem.classes;

import java.util.Date;
import java.util.HashSet;

/**
 * class to represent a student in the system
 */
public class Student {

    private String[] name = new String[2];
    private Date dob;
    private static HashSet<String> students = new HashSet<String>();
    private Statistics statistics = new Statistics(this);



    public Student(String firstName, String surname, Date dob){

        if(firstName == null || surname == null || dob == null || firstName.isEmpty() || surname.isEmpty()){
            throw new IllegalArgumentException("Student must have first name, surname, and date of birth.");
        }

        String studentString;
        studentString = firstName + surname + dob.toString();

        if (students.contains(studentString)) {
            throw new IllegalArgumentException(
                    "This student already exists");
        } else {


            name[0] = firstName;
            name[1] = surname;
            this.dob = dob;
            students.add(studentString);
        }
    }

    /**
     *
     * @return student's full name, with both first name and surname
     */
    public String getName(){
        String fullname = this.name[0] + " " + this.name[1];
        return fullname;
    }

    /**
     *
     * @return the user's associated statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     *
     * @return the user's date of birth
     */
    public Date getDob(){
        return dob;
    }

    /**
     *
     * @return overrides toString
     */
    public String toString(){
        return("Name: " + this.name[0] + " " + this.name[1] + "\n" + "Date of Birth: " + dob.toString());
    }

    /**
     *
     * @param obj
     * @return true if users have same name and date of birth.
     */
    @Override
    public boolean equals(Object obj) {
        final Student other = (Student) obj;

        if(this.name[0].equals(other.name[0]) && this.name[1].equals(other.name[1]) && this.dob.equals(other.dob)){
            return true;
        }
        return false;
    }

}
