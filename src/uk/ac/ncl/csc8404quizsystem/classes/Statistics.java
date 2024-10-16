package uk.ac.ncl.csc8404quizsystem.classes;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private enum verdict {
        PASS, FAIL, TBD
    }


    private verdict verdict;
    private int noOfQuizAttempts;
    private int noOfQuizRevisions;
    private Quiz[] quizzesCompleted;
    private Map<Integer, Integer> quizScores = new HashMap<Integer,Integer>();




    public Statistics(){
        verdict = verdict.TBD;
        noOfQuizAttempts = 0;
        noOfQuizRevisions = 0;
    }

    public void incrementQuizAttempts(){
        noOfQuizAttempts++;
    }
    public void incrementRevisionQuizAttempts(){
        noOfQuizRevisions++;
    }

    public void setVerdict (verdict verdict){this.verdict = verdict;}

    public int getNoOfQuizAttempts (){return noOfQuizAttempts;}

    public int getNoOfQuizRevisions (){return noOfQuizRevisions;}

    public String getStudentInfo(){
        return(super.toString());
    }

    public String toString(){
        return (getStudentInfo() + "\n" + "Verdict: " + verdict.toString() + "\n" + " Number of Quiz Attempts: " + getNoOfQuizAttempts()
        + "\n" + "Number of Revisions: " + getNoOfQuizRevisions());
    }


}
