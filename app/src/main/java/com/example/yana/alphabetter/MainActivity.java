package com.example.yana.alphabetter;

/*
Start menu which allows user to choose quiz or learning mode, with appropriate language options for
each
 */
import android.app.ActionBar;
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

        // set action bar title
        android.support.v7.app.ActionBar mActionBar= getSupportActionBar();
        mActionBar.setTitle("AlphaBetter");
    }

    // starts russian quiz when button is clicked
    public void startRussianQuiz(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 0);
        startActivity(intent);
    }

    // starts learn mode for russian when button is clicked
    public void startLearnRussian(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra(LanguageNum, 0);
        startActivity(intent);
    }

    // starts greek quiz when button is clicked
    public void startGreekQuiz(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 1);
        startActivity(intent);
    }

    // starts learn mode for greek when button is clicked
    public void startLearnGreek(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra(LanguageNum, 1);
        startActivity(intent);
    }

    // starts armenian quiz when button is clicked
    public void startArmenianQuiz(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(LanguageNum, 2);
        startActivity(intent);
    }

    // starts learn mode for armenian when button is clicked
    public void startLearnArmenian(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra(LanguageNum, 2);
        startActivity(intent);
    }





}
