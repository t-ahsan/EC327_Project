/* Created by yana on 11/23/17
   Handles the quiz activity.
   The activity is a multiple choice quiz that displays a letter on the screen,
   and gives 4 different options for answers in the opposite alphabet.
 */


package com.example.yana.alphabetter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yana.alphabetter.RussianLetterMap;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // the database for the questions
    private RussianLetterMap russianLetterMap = new RussianLetterMap();

    // number of options the user is given
    private int nButtons = 4;

    // user's score on the quiz
    private int score = 0;

    // the question number the user is currently on
    private int questionNumber = 0;

    // number of lives user starts with
    private int lives = 3;

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

    private TextView timerView;

    // displays the options for answers
    private Button buttons[] = new Button[nButtons];

    private CountDownTimer timer;

    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup layout
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

        // shuffle letters
       russianLetterMap.shuffleEntries();

        // start quiz
       updateQuestion(questionNumber++);
    }

    private void updateQuestion(int i) {
        // check if end of quiz
        if (i >= russianLetterMap.nEntries || lives <= 0) {
            // go to 'end screen'???
            // TO DO: Implement end screen

            // go back to start menu
            finish();
        }



        // user has not yet answered current question
        questionAnswered = false;

        // update question number
        questionNumberView.setText(Integer.toString(questionNumber) + "/" + Integer.toString(russianLetterMap.nEntries));

        // update score
        scoreView.setText(Integer.toString(score));

        // updates lives
        livesView.setText(Integer.toString(lives));

        // hide result
        resultView.setVisibility(View.INVISIBLE);

        // randomly choose between testing Latin and Cyrillic
        Random random = new Random();
        boolean isTestTargetMode = random.nextBoolean();

        String questionLetter, correctAnswer;
        int incorrectAnswerIndex;
        int incorrectIndices[] = new int[nButtons - 1];
        boolean valueUnique;
        if (isTestTargetMode) {
            //set prompt
            promptView.setText("Choose correct " + russianLetterMap.knownLanguageAlphabetName + " letter");
            // set question
            questionLetter = russianLetterMap.getTargetLanguageEntry(i);
            correctAnswer = russianLetterMap.getKnownLanguageEntry(i);
            questionView.setText(questionLetter);

            // randomly choose a button for correct answer
            correctButton = random.nextInt(nButtons);
            (buttons[correctButton]).setText(correctAnswer);

            // fill in other buttons randomly with incorrect answers
            for (int j = 0; j < nButtons; j++) {
                if (j != correctButton) {
                    do {
                        valueUnique = true;
                        incorrectAnswerIndex = random.nextInt(russianLetterMap.nEntries);
                        //  check to make sure no answer options are repeated
                        for (int k = 0; k < j; k++) {
                            if (incorrectIndices[k] == incorrectAnswerIndex) {
                                valueUnique = false;
                                break;
                            }
                        }
                    } while(!valueUnique || incorrectAnswerIndex == i);
                    buttons[j].setText(russianLetterMap.getKnownLanguageEntry(incorrectAnswerIndex));
                }
            }

        }
        else {
            //set prompt
            promptView.setText("Choose correct " + russianLetterMap.targetLanguageAlphabetName + " letter");

            // set question
            questionLetter = russianLetterMap.getKnownLanguageEntry(i);
            correctAnswer = russianLetterMap.getTargetLanguageEntry(i);
            questionView.setText(questionLetter);

            // randomly choose a button for correct answer
            correctButton = random.nextInt(nButtons);
            (buttons[correctButton]).setText(correctAnswer);

            // fill in other buttons randomly with incorrect answers
            for (int j = 0; j < nButtons; j++) {
                if (j != correctButton) {
                    do {
                        incorrectAnswerIndex = random.nextInt(russianLetterMap.nEntries);
                    } while (incorrectAnswerIndex == i);
                    buttons[j].setText(russianLetterMap.getTargetLanguageEntry(incorrectAnswerIndex));
                }
            }
        }



            // enable user to click buttons
            for (int j = 0; j < nButtons; j++) {
                buttons[j].setClickable(true);
            }

            // set timer

            timer = new CountDownTimer(10000, 1000) {

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

            // wait for user to press one of the buttons
            for (int j = 0; j < nButtons; j++) {
                buttons[j].setOnClickListener(buttonOnClickListener);
            }
    }




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
        // ask if user really wants to quit quiz when back button pressed
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        QuizActivity.super.onBackPressed();
                    }
                }).create().show();
    }

}





