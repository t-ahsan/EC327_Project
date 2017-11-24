/* Created by yana on 11/23/17
   Handles the quiz activity.
   The activity is a multiple choice quiz that displays a Russian letter on the screen,
   and gives 4 different options for answers.
 */


package com.example.yana.alphabetter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yana.alphabetter.LetterMap;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // the database for the questions
    private LetterMap letterMap = new LetterMap();

    // number of options the user is given
    private int nButtons = 4;

    // user's score on the quiz
    private int score = 0;

    // the question number the user is currently on
    private int questionNumber = 0;

    // the correct button for the current question
    private int correctButton;

    // keeps track of if the user has answered the current question
    private boolean questionAnswered = false;

    // displays the user's score
    private TextView scoreView;

    // displays the question
    private TextView questionView;

    // displays the result of the current question
    private TextView resultView;

    // displays the options for answers
    private Button buttons[] = new Button[nButtons];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup layout
        setContentView(R.layout.activity_quiz);

        scoreView = findViewById(R.id.score);
        scoreView.setText(Integer.toString(score));
        questionView = findViewById(R.id.question);
        resultView = findViewById(R.id.result);

        buttons[0] = findViewById(R.id.buttonChoice1);
        buttons[1] = findViewById(R.id.buttonChoice2);
        buttons[2] = findViewById(R.id.buttonChoice3);
        buttons[3] = findViewById(R.id.buttonChoice4);

        // shuffle letters
        letterMap.shuffleLetters();

        // start quiz
       updateQuestion(questionNumber++);
    }

    private void updateQuestion(int i) {
        if (i >= letterMap.nLetters) {
            // go to 'end screen'???
            // TO DO: Implement end screen
        }
        else {
            // user has not yet answered current question
            questionAnswered = false;

            // update score
            scoreView.setText(Integer.toString(score) + "/" + Integer.toString(letterMap.nLetters));

            // hide result
            resultView.setVisibility(View.INVISIBLE);

            // set question
            String questionLetter = letterMap.getRussianLetter(i);
            String correctAnswer = letterMap.getCorrectRomanizedAnswer(questionLetter);
            questionView.setText(questionLetter);

            // randomly choose a button for correct answer
            Random random = new Random();
            correctButton = random.nextInt(nButtons);
            (buttons[correctButton]).setText(correctAnswer);

            // fill in other buttons randomly with incorrect answers
            int incorrectAnswer;
            for (int j = 0; j < nButtons; j++) {
                if (j != correctButton) {
                    do {
                        incorrectAnswer = random.nextInt(letterMap.nLetters);
                    } while (incorrectAnswer == i);
                    buttons[j].setText(letterMap.getRomanizedLetter(incorrectAnswer));
                }
            }

            // wait for user to press one of the buttons
            for (int j = 0; j < nButtons; j++) {
                buttons[j].setOnClickListener(buttonOnClickListener);
            }
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

                // figure out if user clicked correct button
                switch (v.getId()) {
                    case R.id.buttonChoice1:
                        if (correctButton == 0) {
                            buttons[0].setText("Correct!");
                            score += 1;
                            userCorrect = true;
                        } else {
                            buttons[0].setText("Incorrect");
                        }
                        break;
                    case R.id.buttonChoice2:
                        if (correctButton == 1) {
                            buttons[1].setText("Correct!");
                            score += 1;
                            userCorrect = true;
                        } else {
                            buttons[1].setText("Incorrect");
                        }
                        break;
                    case R.id.buttonChoice3:
                        if (correctButton == 2) {
                            buttons[2].setText("Correct!");
                            score += 1;
                            userCorrect = true;
                        } else {
                            buttons[2].setText("Incorrect");
                        }
                        break;
                    case R.id.buttonChoice4:
                        if (correctButton == 3) {
                            buttons[3].setText("Correct!");
                            score += 1;
                            userCorrect = true;
                        } else {
                            buttons[3].setText("Incorrect");
                        }
                        break;
                    default:
                        throw new RuntimeException("Unknown button ID");
                }
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
            }, 1500);


        }
    };

}





