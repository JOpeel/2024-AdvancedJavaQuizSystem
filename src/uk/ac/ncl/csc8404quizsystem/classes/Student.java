package uk.ac.ncl.csc8404quizsystem.classes;

import java.util.Date;
import java.util.HashSet;

public class Student {

    private String[] name;
    private Date dob;
    private static HashSet<String> students = new HashSet<String>();
    //statistics



    public Student(String firstName, String surname, Date dob) {

        String studentString = new String();
        studentString = firstName + surname + dob.toString();

        if (students.contains(studentString)) {
            throw new IllegalArgumentException(
                    "This student already exists");
        } else {


            this.name[0] = firstName;
            this.name[1] = surname;
            this.dob = dob;
            students.add(studentString);
        }
    }

    public String getName(){
        String fullname = this.name[0] + " " + this.name[1];
        return fullname;
    }

    public String getDob(){
        return dob.toString();
    }

    public String toString(){
        return("Name: " + this.name[0] + " " + this.name[1] + "\n" + "Date of Birth: " + dob.toString());
    }

}
