package com.example.yana.alphabetter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Created by yana on 12/9/17
The activity that allows the user to learn the target language alphabet by seeing the letters,
hearing a native speaker pronounce them, and allows the user to practice writing the letters by
drawing on a canvas
 */

public class LearnActivity extends AppCompatActivity {

    // strings for intents
    public static final String language = "language";
    public static final String mode = "learnMode";

    // data for language
    private GenericLetterMap letterMap;

    // the current letter user is learning
    private int letterNumber = 0;

    // displays letter in target language
    private TextView targetLetterView;

    // displays letter in known language (English)
    private TextView knownLetterView;

    // displays letter for user to trace
    private TextView traceLetterView;

    // displays total progress through alphabet
    private TextView progressView;

    private Button previousButton;

    // drawing canvas to practice writing letter
    private LetterCanvas letterCanvas;

    // audio for letter
    private MediaPlayer sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        // get intent to figure out language for quiz
        Intent intent = getIntent();
        int languageIndex = intent.getIntExtra(MainActivity.LanguageNum, 0);

        // Decide which language to use based on button clicked in start screen
        loadLetterMap(languageIndex);

        // set action bar title
        android.support.v7.app.ActionBar mActionBar= getSupportActionBar();
        mActionBar.setTitle("Learning " + letterMap.targetLanguageName);

        //setup layout
        setupLayout();

        // go to first letter
        updateLetter(letterNumber);
    }

    // set up layout for learn activity
    public void setupLayout() {
        targetLetterView = findViewById(R.id.targetLetter);
        knownLetterView = findViewById(R.id.knownLetter);
        traceLetterView = findViewById(R.id.traceLetter);
        progressView = findViewById(R.id.progressText);
        letterCanvas = (LetterCanvas) findViewById(R.id.letterCanvas);
        previousButton = findViewById(R.id.previousButton);
        sound = MediaPlayer.create(LearnActivity.this, letterMap.audioFiles[0]);
    }

    // update layout for current letter
    private void updateLayout(){
        // hide previous button when at first letter
        if (letterNumber == 0) {
            previousButton.setVisibility(View.INVISIBLE);
            previousButton.setClickable(false);
        }
        // show previous button at second letter
        else if (letterNumber == 1) {
            previousButton.setVisibility(View.VISIBLE);
            previousButton.setClickable(true);
        }

        // update text
        targetLetterView.setText(letterMap.getTargetLanguageEntry(letterNumber));
        traceLetterView.setText(letterMap.getTargetLanguageEntry(letterNumber));
        knownLetterView.setText(letterMap.getKnownLanguageEntry(letterNumber));
        progressView.setText((letterNumber+1) + "/" + letterMap.nEntries);

        // erase drawings from previous letter
        letterCanvas.eraseAll();

    }

    // update activity for next letter
    private void updateLetter(int lNum) {
        if (lNum < 0) {
            throw new RuntimeException("Invalid letter number");
        }
        // go to end screen activity when user goes through all letters
        else if (lNum >= letterMap.nEntries) {
            Intent intent = new Intent(this, EndScreenActivity.class);
            int languageIndex = getIntent().getIntExtra(MainActivity.LanguageNum, 0);
            intent.putExtra(language, languageIndex);
            intent.putExtra(mode, 0);
            startActivity(intent);
            finish();
        }
        else {
            // automatically play sound corresponding to letter
            sound = MediaPlayer.create(LearnActivity.this, letterMap.getAudioFileEntry(letterNumber));
            sound.start();

            // release sound when finished playing
            sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }

            });

            // update layout to match new letter
            updateLayout();
        }
    }

    // go to next letter when next button clicked
    public void onNextButtonClick (View view) {
        updateLetter(++letterNumber);
    }

    // clear user's drawing when clear button clicked
    public void onClearButtonClick (View view) {
        letterCanvas.eraseAll();
    }

    // play audio when user clicks audio button
    public void onSoundButtonClick(View view) {
        // load sound corresponding to letter
        sound = MediaPlayer.create(LearnActivity.this, letterMap.getAudioFileEntry(letterNumber));
        sound.start();

        // release sound when finished playing
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });
    }

    // go to previous letter when previous button clicked
    public void onPreviousButtonClick(View view) {
        updateLetter(--letterNumber);
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
}
