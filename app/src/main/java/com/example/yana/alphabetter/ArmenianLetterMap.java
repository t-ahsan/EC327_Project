package com.example.yana.alphabetter;

/**
 * Created by yana on 12/10/2017.
 *
 * Stores data for learn/quiz modes in Armenian
 */

public class ArmenianLetterMap extends GenericLetterMap {
    //array to hold all armenian letters
    private String armenianLetters [] = {
            "Ա, ա",
            "Բ, բ",
            "Գ, գ",
            "Դ, դ",
            "Ե, ե",
            "Զ, զ",
            "Է, է",
            "Ը, ը",
            "Թ, թ",
            "Ժ, ժ",
            "Ի, ի",
            "Լ, լ",
            "Խ, խ",
            "Ծ, ծ",
            "Կ, կ",
            "Հ, հ",
            "Ձ, ձ",
            "Ղ, ղ",
            "Ճ, ճ",
            "Մ, մ",
            "Յ, յ",
            "Ն, ն",
            "Շ, շ",
            "Ո, ո",
            "Չ, չ",
            "Պ, պ",
            "Ջ, ջ",
            "Ռ, ռ",
            "Ս, ս",
            "Վ, վ",
            "Տ, տ",
            "Ր, ր",
            "Ց, ց",
            "Ւ, ւ",
            "Փ, փ",
            "Ք, ք",
            "և",
            "Օ, օ",
            "Ֆ, ֆ"
    };

    // array to hold transliterated version of armenian letters
    private String transliteratedArmenianLetters [] =  {
        "a",
        "b",
        "g",
        "d",
        "e",
        "z",
        "ē",
        "ë",
        "t'",
        "ž",
        "i",
        "l",
        "x",
        "ç",
        "k",
        "h",
        "j",
        "ġ",
        "č̣",
        "m",
        "y",
        "n",
        "š",
        "o",
        "č",
        "p",
        "ǰ",
        "ṙ",
        "s",
        "v",
        "t",
        "r",
        "c'",
        "w",
        "p'",
        "k'",
        "ew",
        "ò",
        "f"
    };

    // array to hold capital version of armenian letters
    private String capitalArmenianLetters[] = {
            "Ա",
            "Բ",
            "Գ",
            "Դ",
            "Ե",
            "Զ",
            "Է",
            "Ը",
            "Թ",
            "Ժ",
            "Ի",
            "Լ",
            "Խ",
            "Ծ",
            "Կ",
            "Հ",
            "Ձ",
            "Ղ",
            "Ճ",
            "Մ",
            "Յ",
            "Ն",
            "Շ",
            "Ո",
            "Չ",
            "Պ",
            "Ջ",
            "Ռ",
            "Ս",
            "Վ",
            "Տ",
            "Ր",
            "Ց",
            "Ւ",
            "Փ",
            "Ք",
            "և",
            "Օ",
            "Ֆ"
    };

    // holds locations for audio for armenian letters
    // audio taken from https://commons.wikimedia.org/wiki/File:Armenian_alphabet_(Eastern_Armenian).ogg
    private int armenianAudioFiles [] = {
            R.raw.ar_1,
            R.raw.ar_2,
            R.raw.ar_3,
            R.raw.ar_4,
            R.raw.ar_5,
            R.raw.ar_6,
            R.raw.ar_7,
            R.raw.ar_8,
            R.raw.ar_9,
            R.raw.ar_10,
            R.raw.ar_11,
            R.raw.ar_12,
            R.raw.ar_13,
            R.raw.ar_14,
            R.raw.ar_15,
            R.raw.ar_16,
            R.raw.ar_17,
            R.raw.ar_18,
            R.raw.ar_19,
            R.raw.ar_20,
            R.raw.ar_21,
            R.raw.ar_22,
            R.raw.ar_23,
            R.raw.ar_24,
            R.raw.ar_25,
            R.raw.ar_26,
            R.raw.ar_27,
            R.raw.ar_28,
            R.raw.ar_29,
            R.raw.ar_30,
            R.raw.ar_31,
            R.raw.ar_32,
            R.raw.ar_33,
            R.raw.ar_34,
            R.raw.ar_35,
            R.raw.ar_36,
            R.raw.ar_37_1,
            R.raw.ar_38_1,
            R.raw.ar_39_1
    };


    // constructor
    public ArmenianLetterMap() {
        super();
        targetLanguageAlphabetName = "Armenian";
        targetLanguageName = "Armenian";
        nEntries = 39;
        entryMap = new String[nEntries][nDimensions];

        // fill entryMap with armenian data
        for (int i = 0; i < nEntries; i++) {
            entryMap[i][targetLanguageIndex] = armenianLetters[i];
            entryMap[i][knownLanguageIndex] = transliteratedArmenianLetters[i];
            entryMap[i][targetCapitalLettersIndex] = capitalArmenianLetters[i];
        }

        audioFiles = armenianAudioFiles;
    }


}
