package com.example.yana.alphabetter;

import android.os.Environment;

import java.io.File;

/**
 * Created by yana on 11/24/2017.
 */

public class RussianLetterMap extends GenericLetterMap {
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

    private int russianAudioFiles[] = {
            R.raw.a,
            R.raw.b,
            R.raw.v,
            R.raw.g,
            R.raw.d,
            R.raw.ye,
            R.raw.yo,
            R.raw.zh,
            R.raw.z,
            R.raw.i,
            R.raw.y_consonant,
            R.raw.k,
            R.raw.l,
            R.raw.m,
            R.raw.n,
            R.raw.o,
            R.raw.p,
            R.raw.r,
            R.raw.s,
            R.raw.t,
            R.raw.u,
            R.raw.f,
            R.raw.kh,
            R.raw.ts,
            R.raw.ch,
            R.raw.sh,
            R.raw.sch,
            R.raw.hardsign,
            R.raw.y_vowel,
            R.raw.softsign,
            R.raw.e,
            R.raw.yu,
            R.raw.ya
    };

    // constructor
    public RussianLetterMap() {
        super();
        targetLanguageAlphabetName = "Cyrillic";
        targetLanguageName = "Russian";
        nEntries = 33;
        entryMap = new String[nEntries][nDimensions];
        for (int i = 0; i < nEntries; i++) {
            entryMap[i][targetLanguageIndex] = russianLetters[i];
            entryMap[i][knownLanguageIndex] = romanizedLetters[i];
        }

        audioFiles = russianAudioFiles;
    }


}