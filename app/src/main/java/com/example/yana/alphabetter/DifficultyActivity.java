package com.example.yana.alphabetter;
/*Created by tahsan on 12/7/17.*/
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class DifficultyActivity extends AppCompatActivity {
    public static final String Difficulty = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    public void startRegular(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        int language = getIntent().getIntExtra(MainActivity.LanguageNum, 0);
        intent.putExtra(MainActivity.LanguageNum, language);
        intent.putExtra(Difficulty, 0);
        startActivity(intent);
        finish();
    }

    public void startHard(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        int language = getIntent().getIntExtra(MainActivity.LanguageNum, 0);
        intent.putExtra(MainActivity.LanguageNum, language);
        intent.putExtra(Difficulty, 1);
        startActivity(intent);
        finish();
    }
}