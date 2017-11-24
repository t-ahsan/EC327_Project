package com.example.yana.alphabetter;

import java.util.Random;

/**
 *
 * Created by yana on 11/23/2017.
 * The database for the quiz
 */

public class LetterMap {
    /* number of letters in Russian alphabet */
    public int nLetters = 33;

    // array of all russian letters
    private String russianLetters [] = {
            "А, а",
            "Б, б",
            "В, в",
            "Г, г",
            "Д, д",
            "Е, е",
            "Ё, ё",
            "Ж, ж",
            "З, з",
            "И, и",
            "Й, й",
            "К, к",
            "Л, л",
            "М, м",
            "Н, н",
            "О, о",
            "П, п",
            "Р, р",
            "С, с",
            "Т, т",
            "У, у",
            "Ф, ф",
            "Х, х",
            "Ц, ц",
            "Ч, ч",
            "Ш, ш",
            "Щ, щ",
            "Ъ, ъ",
            "Ы, ы",
            "Ь, ь",
            "Э, э",
            "Ю, ю",
            "Я, я"
    };

    /* array of latinization of russian letters */
    private String romanizedLetters [] = {
            "a",
            "b",
            "v",
            "g",
            "d",
            "ye",
            "yo",
            "zh",
            "z",
            "i",
            "y (consonant)",
            "k",
            "l",
            "m",
            "n",
            "o",
            "p",
            "r",
            "s",
            "t",
            "u",
            "f",
            "kh",
            "ts",
            "ch",
            "sh",
            "sch",
            "hard sign",
            "y (vowel)",
            "soft sign",
            "e",
            "yu",
            "ya"
    };


    /* returns correct romanizedLetter corresponding to russianLetter */
    public String getCorrectRomanizedAnswer(String rLetter) {
        int correct_index = 0;
        for (int i = 0; i < nLetters; i++) {
            if (russianLetters[i].equals(rLetter)) {
                correct_index = i;
            }
        }
        return romanizedLetters[correct_index];
    }

    /* Shuffles russianLetters and romanizedLetters randomly */
    public void shuffleLetters(){
        int index;
        String rTemp, lTemp;
        Random random = new Random();
        for (int i = nLetters - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            rTemp = russianLetters[index];
            lTemp = romanizedLetters[index];
            russianLetters[index] = russianLetters[i];
            romanizedLetters[index] = romanizedLetters[i];
            russianLetters[i] = rTemp;
            romanizedLetters[i] = lTemp;
        }
    }

    public String getRussianLetter(int index) {
        return russianLetters[index];
    }

    public String getRomanizedLetter(int index) {
        return romanizedLetters[index];
    }

}
