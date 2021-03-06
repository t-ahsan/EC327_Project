package com.example.yana.alphabetter;
/*Created by tahsan on 12/7/17.
* Allows user to choose difficulty for quizActivity
* */
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DifficultyActivity extends AppCompatActivity {
    public static final String Difficulty = "difficulty_string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    // starts quiz of appropriate language and regular difficulty
    public void startRegular(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        int language = getIntent().getIntExtra(MainActivity.LanguageNum, 0);
        intent.putExtra(MainActivity.LanguageNum, language);
        intent.putExtra(Difficulty, 0);
        startActivity(intent);
        finish();
    }

    // starts quiz of appropriate language and hard difficulty
    public void startHard(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        int language = getIntent().getIntExtra(MainActivity.LanguageNum, 0);
        intent.putExtra(MainActivity.LanguageNum, language);
        intent.putExtra(Difficulty, 1);
        startActivity(intent);
        finish();
    }


}
