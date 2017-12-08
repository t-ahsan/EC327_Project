/* Created by yana on 11/23/17
   Handles the quiz activity.
   The activity is a multiple choice quiz that displays a letter on the screen,
   and gives 4 different options for answers in the opposite alphabet.
 */


package com.example.yana.alphabetter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // the database for the questions
    GenericLetterMap letterMap;

    // number of options the user is given
    private int nButtons = 4;

    // user's score on the quiz
    private int score = 0;

    // the question number the user is currently on
    private int questionNumber = 0;

    // instantiates lives
    private int lives;

    // the correct button for the current question
    private int correctButton;

    // keeps track of if the user has answered the current question
    private boolean questionAnswered = false;

    // displays the user's score
    private TextView scoreView;

    // displays the question number
    private TextView questionNumberView;

    // displays the question
    private TextView questionView;

    // displays the result of the current question
    private TextView resultView;

    // displays the prompt for the question
    private TextView promptView;

    // displays the number of lives
    private TextView livesView;

    // displays the status of the timer
    private TextView timerView;

    // displays the options for answers
    private Button buttons[] = new Button[nButtons];

    // timer for each question
    private CountDownTimer timer;

    // time left to answer each question
    private long timeLeft;

    // initial time timer is set to
    private long timeAtStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get intent to figure out language for quiz
        Intent intent = getIntent();
        int languageIndex = intent.getIntExtra(MainActivity.LanguageNum, 0);

        // Decide which language to use based on button clicked in start screen
        loadLetterMap(languageIndex);

        // figure out difficulty for quiz
        int difficultyIndex = getIntent().getIntExtra(DifficultyActivity.Difficulty, 0);
        loadDifficultyLevel(difficultyIndex);

        // setup layout
        setupLayout();

        // shuffle letters
        letterMap.shuffleEntries();

        // start quiz
       updateQuestion(questionNumber++);
    }

    private void updateQuestion(int i) {
        // check if end of quiz
        if (i >= letterMap.nEntries || lives <= 0) {
            // go to 'end screen'???
            // TO DO: Implement end screen

            // go back to start menu
            finish();
        }
        else {

            // update layout
            updateLayout();

            // randomly choose between testing Latin and Foreign Alphabet
            Random random = new Random();
            boolean isTestTargetMode = random.nextBoolean();

            String questionLetter, correctAnswer;
            int incorrectAnswerIndex;
            int incorrectIndices[] = new int[nButtons];
            boolean valueUnique;
            if (isTestTargetMode) {
                //set prompt
                promptView.setText("Choose correct " + letterMap.knownLanguageAlphabetName + " letter");
                // set question
                questionLetter = letterMap.getTargetLanguageEntry(i);
                correctAnswer = letterMap.getKnownLanguageEntry(i);
                questionView.setText(questionLetter);

                // randomly choose a button for correct answer
                correctButton = random.nextInt(nButtons);
                (buttons[correctButton]).setText(correctAnswer);

                // fill in other buttons randomly with incorrect answers, with no duplicates
                for (int j = 0; j < nButtons; j++) {
                    if (j != correctButton) {
                        do {
                            valueUnique = true;
                            incorrectAnswerIndex = random.nextInt(letterMap.nEntries);
                            //  check to make sure no answer options are repeated
                            for (int k = 0; k < j; k++) {
                                if (incorrectIndices[k] == incorrectAnswerIndex) {
                                    valueUnique = false;
                                    break;
                                }
                            }
                        } while (!valueUnique || incorrectAnswerIndex == i);
                        incorrectIndices[j] = incorrectAnswerIndex;
                        buttons[j].setText(letterMap.getKnownLanguageEntry(incorrectAnswerIndex));
                    }
                }

            } else {
                //set prompt
                promptView.setText("Choose correct " + letterMap.targetLanguageAlphabetName + " letter");

                // set question
                questionLetter = letterMap.getKnownLanguageEntry(i);
                correctAnswer = letterMap.getTargetLanguageEntry(i);
                questionView.setText(questionLetter);

                // randomly choose a button for correct answer
                correctButton = random.nextInt(nButtons);
                (buttons[correctButton]).setText(correctAnswer);

                // fill in other buttons randomly with incorrect answers
                for (int j = 0; j < nButtons; j++) {
                    if (j != correctButton) {
                        do {
                            valueUnique = true;
                            incorrectAnswerIndex = random.nextInt(letterMap.nEntries);
                            //  check to make sure no answer options are repeated
                            for (int k = 0; k < j; k++) {
                                if (incorrectIndices[k] == incorrectAnswerIndex) {
                                    valueUnique = false;
                                    break;
                                }
                            }
                        } while (!valueUnique || incorrectAnswerIndex == i);
                        incorrectIndices[j] = incorrectAnswerIndex;
                        buttons[j].setText(letterMap.getTargetLanguageEntry(incorrectAnswerIndex));
                    }
                }
            }


            // enable user to click buttons
            for (int j = 0; j < nButtons; j++) {
                buttons[j].setClickable(true);
            }

            // set timer
            setQuizTimer(timeAtStart);

            // wait for user to press one of the buttons
            for (int j = 0; j < nButtons; j++) {
                buttons[j].setOnClickListener(buttonOnClickListener);
            }
        }
    }



    // listener for all four buttons
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean userCorrect = false;
            Handler handler = new Handler();
            // make sure user doesn't answer question more than once
            if (!questionAnswered) {
                questionAnswered = true;
                timer.cancel();
                // figure out if user clicked correct button
                switch (v.getId()) {
                    case R.id.buttonChoice1:
                        if (correctButton == 0) {
                            score += timeLeft/ 100;
                            userCorrect = true;
                        }
                        else {
                            lives--;
                        }
                        break;
                    case R.id.buttonChoice2:
                        if (correctButton == 1) {
                            score += timeLeft/ 100;
                            userCorrect = true;
                        }
                        else {
                            lives--;
                        }
                        break;
                    case R.id.buttonChoice3:
                        if (correctButton == 2) {
                            score += timeLeft/ 100;
                            userCorrect = true;
                        }
                        else {
                            lives--;
                        }
                        break;
                    case R.id.buttonChoice4:
                        if (correctButton == 3) {
                            score += timeLeft/ 100;
                            userCorrect = true;
                        }
                        else {
                            lives--;
                        }
                        break;
                    default:
                        throw new RuntimeException("Unknown button ID");
                }
            }

            // disable buttons until next question loads
            for (int i = 0; i < nButtons; i++) {
                buttons[i].setClickable(false);
            }

            // show result to user
            if (userCorrect) {
                resultView.setText("Correct!");
            }
            else {
                resultView.setText("Incorrect");
            }
            resultView.setVisibility(View.VISIBLE);

            // go to next question after delay
            handler.postDelayed(new Runnable() {
                public void run() {
                    updateQuestion(questionNumber++);
                }
            }, 1000);


        }
    };

    @Override
    public void onBackPressed() {
        // pause timer
        timer.cancel();
        // ask if user really wants to quit quiz when back button pressed
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // restart timer
                        setQuizTimer(timeLeft);
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        QuizActivity.super.onBackPressed();
                    }
                }).create().show();
    }


    // loads letter map for language
    public void loadLetterMap(int index) {
        switch (index) {
            case 0:
                letterMap = new RussianLetterMap();
                break;
            case 1:
                letterMap = new GreekLetterMap();
                break;
            default:
                throw new RuntimeException("Unknown Language ID");
        }
    }

    // loads variables for difficulty level
    public void loadDifficultyLevel(int difficulty) {
        switch (difficulty) {
            case 0:
                lives = 3;
                timeAtStart = 15000;
                break;
            case 1:
                lives = 1;
                timeAtStart = 3000;
                break;
        }
    }

    // sets up layout for quiz
    private void setupLayout() {
        setContentView(R.layout.activity_quiz);

        scoreView = findViewById(R.id.score);
        scoreView.setText(Integer.toString(score));
        questionView = findViewById(R.id.question);
        resultView = findViewById(R.id.result);
        questionNumberView = findViewById(R.id.questionNumber);
        promptView = findViewById(R.id.prompt);
        livesView = findViewById(R.id.lives);
        timerView = findViewById(R.id.timer);

        buttons[0] = findViewById(R.id.buttonChoice1);
        buttons[1] = findViewById(R.id.buttonChoice2);
        buttons[2] = findViewById(R.id.buttonChoice3);
        buttons[3] = findViewById(R.id.buttonChoice4);
    }

    // updates texts to match new question
    private void updateLayout() {
        // user has not yet answered current question
        questionAnswered = false;

        // update question number
        questionNumberView.setText(Integer.toString(questionNumber) + "/" + Integer.toString(letterMap.nEntries));

        // update score
        scoreView.setText(Integer.toString(score));

        // updates lives
        livesView.setText(Integer.toString(lives));

        // hide result
        resultView.setVisibility(View.INVISIBLE);
    }

    // sets timer and displays countdown
    private void setQuizTimer(long beginTime) {
        timer = new CountDownTimer(beginTime, 10) {

            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                timerView.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerView.setText("Out of time!");
                lives--;
                updateQuestion(questionNumber++);
            }
        }.start();
    }

}







