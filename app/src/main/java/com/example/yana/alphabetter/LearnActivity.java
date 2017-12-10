package com.example.yana.alphabetter;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/*
Created by yana on 12/9/17
The activity that allows the user to learn the target language alphabet by seeing the letters,
hearing a native speaker pronounce them, and allows the user to practice writing the letters by
drawing on a canvas
 */

public class LearnActivity extends AppCompatActivity {

    // data for language
    private GenericLetterMap letterMap;

    // the current letter user is learning
    private int letterNumber = 0;

    // displays letter in target language
    private TextView targetLetterView;

    // displays letter in known language (English)
    private TextView knownLetterView;

    // button for sound
    private ImageButton audioButton;

    // button to go to next letter
    private Button nextButton;

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

        //setup layout
        setupLayout();

        updateLetter(letterNumber);
    }

    // set up layout for learn activity
    public void setupLayout() {
        targetLetterView = findViewById(R.id.targetLetter);
        knownLetterView = findViewById(R.id.knownLetter);
        nextButton = findViewById(R.id.nextButton);
        audioButton = findViewById(R.id.audioButton);
        letterCanvas = (LetterCanvas) findViewById(R.id.letterCanvas);
        sound = MediaPlayer.create(LearnActivity.this, letterMap.audioFiles[0]);
    }

    // update layout for current letter
    private void updateLayout(){
        targetLetterView.setText(letterMap.getTargetLanguageEntry(letterNumber));
        knownLetterView.setText(letterMap.getKnownLanguageEntry(letterNumber));
        letterCanvas.eraseAll();

    }

    // update activity for next letter
    private void updateLetter(int lNum) {
        if (lNum >= letterMap.nEntries) {

            finish();
        }
        else {
            updateLayout();
        }
    }

    public void onNextButtonClick (View view) {
        updateLetter(++letterNumber);
    }

    public void onClearButtonClick (View view) {
        letterCanvas.eraseAll();
    }

    // play audio when user clicks audio button
    public void onSoundButtonClick(View view) {
        sound = MediaPlayer.create(LearnActivity.this, letterMap.audioFiles[letterNumber]);
        sound.start();

        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });
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
}
