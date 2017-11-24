package com.example.yana.alphabetter;

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

    // constructor
    public RussianLetterMap() {
        super();
        nEntries = 33;
        targetLanguageEntries = russianLetters;
        knownLanguageEntries = romanizedLetters;
    }


}
