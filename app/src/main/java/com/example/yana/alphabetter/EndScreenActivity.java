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
        boolean didUserWin = getIntent().getBooleanExtra(QuizActivity.win, true);
        String scoreString = "" + score;
        String winMessage;
        if (didUserWin){
            winMessage = "YOU WON!";
        }
        else {
            winMessage = "YOU LOST!";
        }

        TextView tv = findViewById(R.id.textView5);
        tv.setText(winMessage);
        tv.invalidate();

        TextView tv2 = findViewById(R.id.textView6);
        tv2.setText("Your score was: " + scoreString);
    }

    public void Go_To_Beginning_Screen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
