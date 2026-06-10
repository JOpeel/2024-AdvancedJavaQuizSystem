package uk.ac.ncl.csc8404quizsystem.classes;

import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.*;

/**
 * class represents the statistics for a given user
 */
public class Statistics {

    public enum verdict {
        PASS, FAIL, TBD
    }


    private verdict verdict;
    private int noOfQuizAttempts;
    private int noOfQuizRevisions;
    private HashSet<Quiz> quizzesCompleted = new HashSet<Quiz>();
    private HashSet<RevisionQuiz> revisionCompleted = new HashSet<RevisionQuiz>();
    private Map<Integer, Float> quizScores = new HashMap<Integer, Float>();
    private Map<Integer, Float> revisionScores = new HashMap<Integer, Float>();
    private ArrayList<Question> questionsLearnt = new ArrayList<Question>();
    private Student student;


    /**
     *
     * @param student
     */
    public Statistics(Student student){
        verdict = verdict.TBD;
        noOfQuizAttempts = 0;
        noOfQuizRevisions = 0;
        this.student = student;
    }

    /**
     * increments users attempts at taking quizzes, setting verdict to fail if taken 2.
     */
    public void incrementQuizAttempts(){
        noOfQuizAttempts++;
        if(noOfQuizAttempts >= 2){
            verdict = verdict.FAIL;
        }
    }

    /**
     * as above but for revision quizzes
     */
    public void incrementRevisionQuizAttempts(){
        noOfQuizRevisions++;
    }

    /**
     *
     * @param verdict
     */
    public void setVerdict (verdict verdict){this.verdict = verdict;}

    /**
     *
     * @return number of quiz attempts
     */
    public int getNoOfQuizAttempts (){return noOfQuizAttempts;}

    /**
     *
     * @return number of quiz revisions
     */
    public int getNoOfQuizRevisions (){return noOfQuizRevisions;}

    /**
     *
     * @return Associated student's info
     */
    public String getStudentInfo(){
        return(student.toString());
    }

    /**
     *
     * @return questions the user has learnt
     */
    public ArrayList<Question> getQuestionsLearnt (){return questionsLearnt;}

    /**
     * adds questions user has learnt (gotten correct) to list
     * @param questionsLearnt as list of questions
     */
    public void addQuestionsLearnt (ArrayList<Question> questionsLearnt) {
        this.questionsLearnt.addAll(questionsLearnt);
    }

    /**
     * adds quiz user has completed to list, and puts score in hashmap
     * @param quiz
     * @param score
     */
    public void addQuizCompleted(Quiz quiz, float score){
        quizzesCompleted.add(quiz);
        quizScores.put(quiz.getQuizId(), score);
    }

    /**
     * as above but for revision
     * @param quiz
     * @param score
     */
    public void addRevisionCompleted(RevisionQuiz quiz, float score){
        revisionCompleted.add(quiz);
        revisionScores.put(quiz.getQuizId(), score);
    }

    /**
     *
     * @return the quizzes the user has completed
     */
    public HashSet<Quiz> getQuizzesCompleted(){
        return(quizzesCompleted);
    }

    /**
     *
     * @return revision quizzes the user has completed
     */
    public HashSet<RevisionQuiz> getRevisionCompleted(){
        return(revisionCompleted);
    }


    /**
     *
     * @param quizId
     * @return the user's score on a given quiz from ID
     */
    public float getQuizScore(int quizId){
        return quizScores.get(quizId);
    }

    /**
     *
     * @param RevisionId
     * @return as above but for Revision quizzes
     */
    public float getRevisionScore(int RevisionId){
        return revisionScores.get(RevisionId);
    }

    /**
     *
     * @return list of quizzes and revision quizzes user has completed and their score on them
     */
    public String printQuizAndScore(){
        StringBuilder output = new StringBuilder();
        Iterator<Integer> quizIterator = quizScores.keySet().iterator();
        Iterator<Integer> revisionIterator = revisionScores.keySet().iterator();
        int currentIteration;

        while(quizIterator.hasNext()){
            currentIteration = quizIterator.next();
            output.append("Quiz ID: ");
            output.append(currentIteration);
            output.append(" Score: ");
            output.append(quizScores.get(currentIteration));
            output.append("\n");

        }
        currentIteration = 0;
        while(revisionIterator.hasNext()){
            currentIteration = revisionIterator.next();
            output.append("Revision Quiz ID: ");
            output.append(currentIteration);
            output.append(" Score: ");
            output.append(revisionScores.get(currentIteration));
            output.append("\n");
        }

        return output.toString();
    }

    /**
     *
     * @return user's verdict (pass, fail, tbd)
     */
    public verdict getVerdict (){return verdict;}

    /**
     *
     * @return overrides toString to include student info etc.
     */
    public String toString(){
        return (getStudentInfo() + "\n" + "Verdict: " + verdict.toString() + "\n" + "Number of Quiz Attempts: " + getNoOfQuizAttempts()
        + "\n" + "Number of Revisions: " + getNoOfQuizRevisions()) + "\n" + printQuizAndScore();
    }


}
