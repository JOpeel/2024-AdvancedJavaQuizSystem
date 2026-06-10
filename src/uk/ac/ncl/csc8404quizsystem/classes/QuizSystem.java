package uk.ac.ncl.csc8404quizsystem.classes;

import uk.ac.ncl.csc8404quizsystem.factories.question.QuestionFactory;
import uk.ac.ncl.csc8404quizsystem.interfaces.Question;

import java.util.*;

/**
 * Singleton class for managing the quiz system
 */
public class QuizSystem {
    private static final QuizSystem SYSTEM = new QuizSystem();
    public ArrayList<Question> questionPool = new ArrayList<>();
    public HashSet<Question> testQuizQuestions = new HashSet<>();
    public Quiz testQuiz;

    private QuizSystem(){

        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is a zebra? a: black, b: yellow, c: white", "a,c"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is an orange? a: green, b: blue, c: orange", "c"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is a kiwi? a: green, b: blue, c: orange", "a"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colours can bananas be? a: green, b: blue, c: orange, d: yellow", "a,d"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is a carrot? a: green, b: blue, c: orange", "c"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colours can onions be? a: yellow, b: brown, c: red, d: blue", "a,b,c"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is a tomato? a: red, b: blue, c: orange", "a"));
        questionPool.add(QuestionFactory.getInstance("Multiple Choice", "What colour is a aubergine? a: green, b: purple", "b"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is a gherkin?", "green"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is a flamingo?", "pink"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is the biggest whale?", "blue"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is a pumpkin?", "orange"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is a garlic bulb?", "white"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is a coconut on the outside?", "brown"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "What colour is celery?", "green"));
        questionPool.add(QuestionFactory.getInstance("Free Response", "what colour is the sky?", "bright blue"));

        testQuizQuestions.add(questionPool.get(0));
        testQuizQuestions.add(questionPool.get(1));
        testQuizQuestions.add(questionPool.get(2));
        testQuizQuestions.add(questionPool.get(8));
        testQuizQuestions.add(questionPool.get(9));
        testQuiz = new Quiz(testQuizQuestions);
        System.out.println("Quiz System Initialized");
    }

    public static QuizSystem getInstance(){
        return SYSTEM;
    }

    /**
     *
     * @param noOfQuestions
     * @param quizQuestionPool
     * @return new Quiz as generated from arguments
     */
    public Quiz generateQuiz(int noOfQuestions, ArrayList<Question> quizQuestionPool){

        boolean containsMCQ = false;
        boolean containsFRQ = false;
        boolean poolContainsMCQ = false;
        boolean poolContainsFRQ = false;
        Random rand = new Random();
        HashSet<Question> questions = new HashSet<Question>();
        Iterator<Question> checkQuestionTypeIterator = quizQuestionPool.iterator();

        while(checkQuestionTypeIterator.hasNext()){
            if(checkQuestionTypeIterator.next().getQuestionType().equals("MCQ")){
                poolContainsMCQ = true;
            }
            if(checkQuestionTypeIterator.next().getQuestionType().equals("FRQ")){
                poolContainsFRQ = true;
            }
        }

        while(questions.size() != noOfQuestions){
            int n = rand.nextInt(quizQuestionPool.size());
            questions.add(quizQuestionPool.get(n));
            if(quizQuestionPool.get(n).getQuestionType().equals("Multiple Choice")){
                containsMCQ = true;
            } else if (quizQuestionPool.get(n).getQuestionType().equals("Free Response")){
                poolContainsFRQ = true;
            }
        }

        if(!(containsMCQ && containsFRQ) && ((poolContainsMCQ) & (poolContainsFRQ))){

            if(!containsMCQ){
                Question removeAt;
                removeAt = questions.iterator().next();
                questions.remove(removeAt);

                int n = rand.nextInt(quizQuestionPool.size());
                while(!(quizQuestionPool.get(n).getQuestionType().equals("Multiple Choice"))){
                    n = rand.nextInt(quizQuestionPool.size());
                }
                questions.add(quizQuestionPool.get(n));

            }
            if(!poolContainsFRQ){
                Question removeAt;
                removeAt = questions.iterator().next();
                questions.remove(removeAt);

                int n = rand.nextInt(quizQuestionPool.size());
                while(!(quizQuestionPool.get(n).getQuestionType().equals("Free Response"))){
                    n = rand.nextInt(quizQuestionPool.size());
                }
                questions.add(quizQuestionPool.get(n));

            }


        }
        Quiz newQuiz = new Quiz(questions);
        return newQuiz;
    }

    /**
     *
     * @param student
     * @param noOfQuestions
     * @param quizQuestionPool
     * @return new revisionQuiz as generated from arguments
     */
    public RevisionQuiz revise(Student student, int noOfQuestions, ArrayList<Question> quizQuestionPool){
        boolean containsMCQ = false;
        boolean containsFRQ = false;
        boolean poolContainsMCQ = false;
        boolean poolContainsFRQ = false;
        Random rand = new Random();
        HashSet<Question> questions = new HashSet<Question>();


        ArrayList<Question> tempQuestionPool = new ArrayList<Question>();
        tempQuestionPool = quizQuestionPool;

        tempQuestionPool.removeAll(student.getStatistics().getQuestionsLearnt());

        Iterator<Question> checkRevisionQuestionTypeIterator = tempQuestionPool.iterator();

        if(tempQuestionPool.size() < noOfQuestions){
            throw new IllegalArgumentException("The number of questions specified for the student to revise is greater than the number of questions they haven't learnt.");
        }

        while(checkRevisionQuestionTypeIterator.hasNext()){
            if(checkRevisionQuestionTypeIterator.next().getQuestionType().equals("MCQ")){
                poolContainsMCQ = true;
            }
            if(checkRevisionQuestionTypeIterator.next().getQuestionType().equals("FRQ")){
                poolContainsFRQ = true;
            }
        }


        while(questions.size() != noOfQuestions){
            int n = rand.nextInt(tempQuestionPool.size());
            questions.add(tempQuestionPool.get(n));
            if(tempQuestionPool.get(n).getQuestionType().equals("Multiple Choice")){
                containsMCQ = true;
            } else if (tempQuestionPool.get(n).getQuestionType().equals("Free Response")){
                containsFRQ = true;
            }
        }

        int i = 0;
        if(!(containsMCQ && containsFRQ) && ((poolContainsMCQ) & (poolContainsFRQ))){

            if(!containsMCQ){
                Question removeAt;
                removeAt = questions.iterator().next();
                questions.remove(removeAt);

                int n = rand.nextInt(tempQuestionPool.size());
                while(!(tempQuestionPool.get(n).getQuestionType().equals("Multiple Choice") && i<tempQuestionPool.size())){
                    n = rand.nextInt(tempQuestionPool.size());
                    i+=1;
                }
                questions.add(tempQuestionPool.get(n));

            }
            if(!containsFRQ){
                Question removeAt;
                removeAt = questions.iterator().next();
                questions.remove(removeAt);

                int n = rand.nextInt(tempQuestionPool.size());
                while(!(tempQuestionPool.get(n).getQuestionType().equals("Free Response") && i<tempQuestionPool.size())){
                    n = rand.nextInt(tempQuestionPool.size());
                    i+=1;
                }
                questions.add(tempQuestionPool.get(n));

            }

        }

        RevisionQuiz newQuiz = new RevisionQuiz(questions);
        return newQuiz;
    }

    /**
     *
     * @param student
     * @param answers
     * @param quiz
     * @return result as decimal between 0 and 1 representing users score on that quiz
     */
    public float takeQuiz(Student student, String[] answers, Quiz quiz){

        if ((student.getStatistics().getVerdict() == Statistics.verdict.PASS) || student.getStatistics().getVerdict() == Statistics.verdict.FAIL){
            throw new IllegalStateException("student has already passed or failed");
        }

        int noQuestionsCorrect = 0;
        float score;
        ArrayList<Question> questionsCorrect = new ArrayList<Question>();
        Question currentQuestion;

        Iterator<Question> questionIterator = quiz.getQuestions().iterator();

        int i = 0;
        while(questionIterator.hasNext()){
            currentQuestion = questionIterator.next();
            if(currentQuestion.checkAnswer(answers[i]) == true){
                noQuestionsCorrect +=1;
                questionsCorrect.add(currentQuestion);
            }
            i += 1;
        }

        score = (float) 1 /((float) quiz.getQuestions().size() / (float) noQuestionsCorrect);

        student.getStatistics().addQuestionsLearnt(questionsCorrect);
        student.getStatistics().incrementQuizAttempts();
        student.getStatistics().addQuizCompleted(quiz, score);

            if(score >= 0.5){
                student.getStatistics().setVerdict(Statistics.verdict.PASS);
            }

        return score;
    }

    /**
     *
     * @param student
     * @param answers
     * @param quiz
     * @return Float between 0 and 1 representing user's score on revision quiz
     */
    public float takeRevisionQuiz(Student student, String[] answers, RevisionQuiz quiz){

        if ((student.getStatistics().getVerdict() == Statistics.verdict.PASS) || student.getStatistics().getVerdict() == Statistics.verdict.FAIL){
            throw new IllegalStateException("student has already passed or failed");
        }

        if ((student.getStatistics().getNoOfQuizRevisions() >= 2)){
            throw new IllegalStateException("Student has reached limit of revisions before attempting normal quiz");
        }

        int noQuestionsCorrect = 0;
        float score;
        ArrayList<Question> questionsCorrect = new ArrayList<Question>();
        Question currentQuestion;

        Iterator<Question> questionIterator = quiz.getQuestions().iterator();
        HashSet<Question> questions = quiz.getQuestions();

        int i = 0;
        while(questionIterator.hasNext()){
            currentQuestion = questionIterator.next();
            System.out.println(currentQuestion);
            if(currentQuestion.checkAnswer(answers[i]) == true){
                noQuestionsCorrect +=1;
                questionsCorrect.add(currentQuestion);
            }
            i += 1;
        }

        score = (float) 1 /((float) quiz.getQuestions().size() /noQuestionsCorrect);

        student.getStatistics().addQuestionsLearnt(questionsCorrect);
        student.getStatistics().incrementRevisionQuizAttempts();
        student.getStatistics().addRevisionCompleted(quiz, score);

        return score;
    }

    /**
     *
     * @param student
     * @return statistics for given student
     */
    public String generateStatistics(Student student){
        return student.getStatistics().toString();
    }


}
