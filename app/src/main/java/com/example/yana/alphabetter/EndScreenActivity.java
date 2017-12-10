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
        String scoreString = "" + score;
        String won;
        if (winIndex == 1){
            won = "YOU WON!!";
        }
        else won = "YOU LOST!!";

        TextView tv = findViewById(R.id.textView5);
        tv.setText(won);

        TextView tv2 = findViewById(R.id.textView6);
        tv2.setText("Your score is : " + scoreString);
    }

    public void Go_To_Beginning_Screen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
