package com.example.yana.alphabetter;

/*created by Lizzy 12/9/2017
* Displays the results of the previous LearnActivity or QuizActivity*/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end__screen);

        // get score, language, and whether user won or not from intent
        int score = getIntent().getIntExtra(QuizActivity.scoreString, 0);
        int winIndex = getIntent().getIntExtra(QuizActivity.win, 0);
        int mode = getIntent().getIntExtra(LearnActivity.mode, 1);
        int languageIndex = getIntent().getIntExtra(LearnActivity.language, 0);

        String scoreString = "" + score;
        String wonString;
        String language;

        // determine language
        switch (languageIndex) {
            case 0:
                language = "Russian";
                break;

            case 1:
                language = "Greek";
                break;

            case 2:
                language = "Armenian";
                break;

            default:
                throw new RuntimeException("Invalid language");
        }

        if (winIndex == 1){
            wonString = "YOU WON!!";
        }
        else {
            wonString = "YOU LOST!!";
        }

        // display results
        TextView tv = findViewById(R.id.textView5);
        TextView tv2 = findViewById(R.id.textView6);

        // display results for quizActivity
        if (mode == 1) {
            tv.setText(wonString);
            tv2.setVisibility(View.VISIBLE);
            tv2.setText("Your score is : " + scoreString);

        }
        // display results for learnActivity
        else {
            tv.setText("Congratulations! You've finished learning the " + language + " alphabet!");
            tv2.setVisibility(View.GONE);
        }

    }

    // go back to main menu when button clicked
    public void Go_To_Beginning_Screen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
