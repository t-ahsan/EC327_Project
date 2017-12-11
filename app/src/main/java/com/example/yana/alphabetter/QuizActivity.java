/* Created by yana on 11/23/17
   Handles the quiz activity.
   The activity is a multiple choice quiz that displays a letter on the screen (or plays audio),
   and gives 4 different options for answers.
 */


package com.example.yana.alphabetter;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    // the database for the questions
    GenericLetterMap letterMap;

    public static final String win = "1";
    public static final String scoreString = "2";
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
    private ImageView livesView;

    // displays the status of the timer
    private TextView timerView;

    // displays the options for answers
    private Button buttons[] = new Button[nButtons];

    private ImageButton questionAudioButton;

    // timer for each question
    private CountDownTimer timer;

    private boolean timerInPlay = false;

    // time left to answer each question
    private long timeLeft;

    // initial time timer is set to
    private long timeAtStart;

    // boolean to determine if user is playing hard mode
    private boolean isHardMode = false;

    // media player for all sounds in quiz
    private MediaPlayer sound;

    // audio file to be played
    private int questionAudio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get intent to figure out language for quiz
        Intent intent = getIntent();
        int languageIndex = intent.getIntExtra(MainActivity.LanguageNum, 0);

        // Decide which language to use based on button clicked in start screen
        loadLetterMap(languageIndex);

        // set action bar title
        android.support.v7.app.ActionBar mActionBar= getSupportActionBar();
        mActionBar.setTitle("Quizzing " + letterMap.targetLanguageName);

        // figure out difficulty for quiz
        int difficultyIndex = getIntent().getIntExtra(DifficultyActivity.Difficulty, 0);
        loadDifficultyLevel(difficultyIndex);

        // setup layout
        setupLayout();

        // shuffle letters
        letterMap.shuffleEntries();

        // start quiz
       updateQuestion(questionNumber);
    }

    // updates quiz to next question
    private void updateQuestion(int i) {

        // user loses
        if (lives <= 0){
            Intent intent = new Intent(this, EndScreenActivity.class);
            intent.putExtra(win, 0);
            intent.putExtra(scoreString, score);

            // go to end screen
            startActivity(intent);
            finish();
        }
        // user completes quiz and wins
        else if (i >= letterMap.nEntries){
            Intent intent = new Intent(this, EndScreenActivity.class);
            intent.putExtra(win, 1);
            intent.putExtra(scoreString, score);

            // go to end screen
            startActivity(intent);

            // finish activity
            finish();
        }

        else {

            // update layout
            updateLayout();

            // randomly choose between testing Latin, target language, or audio
            Random random = new Random();
            int testMode = random.nextInt(3);

            String questionLetter, correctAnswer;
            int incorrectAnswerIndex;
            int incorrectIndices[] = new int[nButtons];
            boolean valueUnique;

            // test with target language
            if (testMode == 0) {
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

            }
            // test with latin
            else if (testMode == 1) {
                //set prompt
                promptView.setText("Choose correct " + letterMap.targetLanguageAlphabetName + " letter");

                // set question
                questionLetter = letterMap.getKnownLanguageEntry(i);
                correctAnswer = letterMap.getTargetCapitalLetterEntry(i);
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
                        buttons[j].setText(letterMap.getTargetCapitalLetterEntry(incorrectAnswerIndex));
                    }
                }
            }
            // test with audio
            else {
                //set prompt
                promptView.setText("Choose correct " + letterMap.targetLanguageAlphabetName + " letter");

                // set correct audio
                questionAudio = letterMap.getAudioFileEntry(i);

                // play audio
                sound = MediaPlayer.create(QuizActivity.this, questionAudio);
                sound.start();

                // release audio when finished playing
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }

                });

                // show audio button, hide text prompt
                questionView.setVisibility(View.INVISIBLE);
                questionAudioButton.setVisibility(View.VISIBLE);
                questionAudioButton.setClickable(true);

                // get correct answer
                correctAnswer = letterMap.getTargetCapitalLetterEntry(i);

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
                        buttons[j].setText(letterMap.getTargetCapitalLetterEntry(incorrectAnswerIndex));
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


    // listener for all answer buttons
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // cancel timer
            timer.cancel();
            timerInPlay = false;

            boolean userCorrect = false;
            Handler handler = new Handler();
            // figure out if user clicked correct button
            switch (v.getId()) {
                case R.id.buttonChoice1:
                    if (correctButton == 0) {
                        score += 100*timeLeft/timeAtStart;
                        userCorrect = true;
                    }
                    else {
                        lives--;
                    }
                    break;

                case R.id.buttonChoice2:
                    if (correctButton == 1) {
                        score += 100*timeLeft/timeAtStart;
                        userCorrect = true;
                    }
                    else {
                        lives--;
                    }
                    break;

                case R.id.buttonChoice3:
                    if (correctButton == 2) {
                        score += 100*timeLeft/timeAtStart;
                        userCorrect = true;
                    }
                    else {
                        lives--;
                    }
                    break;

                case R.id.buttonChoice4:
                    if (correctButton == 3) {
                        score += 100*timeLeft/timeAtStart;
                        userCorrect = true;
                    }
                    else {
                        lives--;
                    }
                    break;

                default:
                    throw new RuntimeException("Unknown button ID");
            }

            // disable buttons until next question loads
            for (int i = 0; i < nButtons; i++) {
                buttons[i].setClickable(false);
            }

            // show result to user and inform result with appropriate sound
            if (userCorrect) {
                resultView.setText("Correct!");
                sound = MediaPlayer.create(QuizActivity.this, R.raw.correct);
                sound.start();
            }
            else {
                sound = MediaPlayer.create(QuizActivity.this, R.raw.incorrect);
                sound.start();
                resultView.setText("Incorrect");
            }

            // release sound after it finishes playing
            sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }

            });

            // show result
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
        confirmQuitQuiz();
    }


    // ask if user really wants to quit quiz
    public void confirmQuitQuiz() {
        // pause timer
        timer.cancel();
        timerInPlay = false;
        // ask if user really wants to quit quiz
        new AlertDialog.Builder(this)
                .setTitle("Quit Quiz?")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
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
            case 2:
                letterMap = new ArmenianLetterMap();
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
                timeAtStart = 10000;
                isHardMode = true;
                break;
        }
    }

    // sets up layout for quiz
    private void setupLayout(){
        setContentView(R.layout.activity_quiz);

        scoreView = findViewById(R.id.score);
        scoreView.setText(Integer.toString(score));
        questionView = findViewById(R.id.question);
        resultView = findViewById(R.id.result);
        questionNumberView = findViewById(R.id.questionNumber);
        promptView = findViewById(R.id.prompt);
        livesView = findViewById(R.id.imageView);
        timerView = findViewById(R.id.timer);

        buttons[0] = findViewById(R.id.buttonChoice1);
        buttons[1] = findViewById(R.id.buttonChoice2);
        buttons[2] = findViewById(R.id.buttonChoice3);
        buttons[3] = findViewById(R.id.buttonChoice4);

        questionAudioButton = findViewById(R.id.questionAudioButton);
    }

    // updates texts to match new question
    private void updateLayout() {
        // update question number
        questionNumberView.setText(Integer.toString(questionNumber) + "/" + Integer.toString(letterMap.nEntries));

        // update score
        scoreView.setText(Integer.toString(score));

        // updates lives
        if (lives == 3){
            livesView.setImageResource(R.drawable.three_hearts);
        }
        else if (lives == 2){
            livesView.setImageResource(R.drawable.two_hearts);
        }
        else if (lives == 1){
            livesView.setImageResource(R.drawable.one_heart);
        }
        else if (lives == 0){
            livesView.setImageResource(R.drawable.zero_hearts);
        }
        else {
            throw new RuntimeException("Invalid lives");
        }

        // hide result
        resultView.setVisibility(View.INVISIBLE);
        questionAudioButton.setVisibility(View.INVISIBLE);
        questionAudioButton.setClickable(false);
        questionView.setVisibility(View.VISIBLE);


        // shorten timer for hard mode
        if (isHardMode) {
            timeAtStart -= 10000 / letterMap.nEntries;
        }
    }

    // sets timer and displays countdown
    private void setQuizTimer(long beginTime) {
        timer = new CountDownTimer(beginTime, 10) {

            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                timerView.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                resultView.setText("Out of time!");
                resultView.setVisibility(View.VISIBLE);
                lives--;
                // disable buttons until next question loads
                for (int i = 0; i < nButtons; i++) {
                    buttons[i].setClickable(false);
                }

                // go to next question after delay
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        updateQuestion(questionNumber++);
                    }
                }, 500);

            }
        }.start();
        timerInPlay = true;
    }

    // replay question audio when question audio button is clicked
    public void onQuestionAudioButtonClick(View view) {
        sound = MediaPlayer.create(QuizActivity.this, questionAudio);
        sound.start();

    }

    @Override
    public void onPause() {
        super.onPause();

        // pause timer
        timer.cancel();
        timerInPlay = false;
    }

    @Override
    public void onStop(){
        super.onStop();

        // pause timer
        timer.cancel();
        timerInPlay = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        // resume timer
        if (!timerInPlay) {
            setQuizTimer(timeLeft);
        }
    }

}







