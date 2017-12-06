package com.example.yana.alphabetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_INT = "com.example.myfirstapp.INT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // starts quiz when start button is clicked
    public void startRussianQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(EXTRA_INT, 0);
        startActivity(intent);
    }

    public void startGreekQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(EXTRA_INT, 1);
        startActivity(intent);
    }
}
