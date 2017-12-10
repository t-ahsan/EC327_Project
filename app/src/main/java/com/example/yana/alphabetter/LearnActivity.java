package com.example.yana.alphabetter;

import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LearnActivity extends AppCompatActivity {

    private GenericLetterMap letterMap;
    private int letterNumber = 0;
    private TextView targetLetterView;
    private TextView knownLetterView;
    private ImageButton audioButton;
    private Button nextButton;
    private LetterCanvas letterCanvas;
    private MediaPlayer sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        letterMap = new RussianLetterMap();
        letterCanvas = (LetterCanvas) findViewById(R.id.letterCanvas);
        sound = MediaPlayer.create(LearnActivity.this, letterMap.audioFiles[0]);
        setupLayout();
        updateLetter(letterNumber);
    }

    public void setupLayout() {
        targetLetterView = findViewById(R.id.targetLetter);
        knownLetterView = findViewById(R.id.knownLetter);
        nextButton = findViewById(R.id.nextButton);
        audioButton = findViewById(R.id.audioButton);
    }

    private void updateLayout(){
        targetLetterView.setText(letterMap.getTargetLanguageEntry(letterNumber));
        knownLetterView.setText(letterMap.getKnownLanguageEntry(letterNumber));
    }

    private void updateLetter(int lNum) {
        if (lNum >= letterMap.nEntries) {

            finish();
        }
        else {
            updateLayout();
            //letterCanvas.drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            if (!letterCanvas.eraseAll()) {

            }
            nextButton.setOnClickListener(nextButtonOnClickListener);
        }
    }

    private View.OnClickListener nextButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateLetter(++letterNumber);
        }
    };

    public void onSoundButtonClick(View view) {
        if (sound.isPlaying()) {
            sound.stop();
        }
        sound = MediaPlayer.create(LearnActivity.this, letterMap.audioFiles[letterNumber]);
        sound.start();
    }
}
