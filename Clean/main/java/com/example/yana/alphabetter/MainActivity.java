package com.example.yana.alphabetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String LanguageNum = "language";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // starts russian quiz when button is clicked
    public void startRussianQuiz(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 0);
        startActivity(intent);
    }

    public void startLearnRussian(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 0);
        startActivity(intent);
    }

    // starts greek quiz when button is clicked
    public void startGreekQuiz(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 1);
        startActivity(intent);
    }
}
