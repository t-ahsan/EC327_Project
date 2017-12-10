package com.example.yana.alphabetter;
/*created by Lizzy 12/9/2017 */

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

        int score = getIntent().getIntExtra(QuizActivity.scoreString, 0);
        int winIndex = getIntent().getIntExtra(QuizActivity.win, 0);
        int mode = getIntent().getIntExtra(LearnActivity.mode, 1);
        int languageIndex = getIntent().getIntExtra(LearnActivity.language, 0);

        String scoreString = "" + score;
        String won = "";
        String language = "";

        if (languageIndex == 1){
            language = "Greek";
        }
        else if (languageIndex == 0) {
            language = "Russian";
        }

        if (winIndex == 1){
            won = "YOU WON!!";
        }
        else won = "YOU LOST!!";

        TextView tv = findViewById(R.id.textView5);
        TextView tv2 = findViewById(R.id.textView6);
        if (mode == 1) {
            tv.setText(won);
            tv2.setVisibility(View.VISIBLE);
            tv2.setText("Your score is : " + scoreString);

        }
        else {
            tv.setText("Congratulations! You've finished learning the " + language + " alphabet!");
            tv2.setVisibility(View.GONE);

        }

    }

    public void Go_To_Beginning_Screen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
