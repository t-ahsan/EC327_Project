package com.example.yana.alphabetter;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by yana on 11/24/2017.
 * A framework to hold all of the data needed for LearnActivity and QuizActivity
 */

public class GenericLetterMap {
    // number of rows in entryMap
    public int nEntries;

    // the number of columns in entryMap
    public final int nDimensions = 3;

    // array that holds all question/answer pairs. First column is entries from the target language
    // the user is learning, and the second column is corresponding entries from the known language
    // (presumably English)
    protected String entryMap[][] = new String[nEntries][nDimensions];

    // the column that holds letters in the target language
    protected final int targetLanguageIndex = 0;

    // the column that hold the version of the letters in the known language (English/latin)
    protected final int knownLanguageIndex = 1;

    // the column that holds the capital letters of the target language
    protected final int targetCapitalLettersIndex = 2;

    // the name of the alphabet of the target language
    public String targetLanguageAlphabetName;
    public String knownLanguageAlphabetName = "Latin";

    // the name of the target language
    public String targetLanguageName;
    public String knownLanguageName = "English";

    // an array that holds the location of audio files for the target language
    protected int audioFiles[] = new int [nEntries];


    // shuffle all entries randomly, while still maintaining same indices for corresponding
    // target/known language pairs
    public void shuffleEntries() {
        int index;
        String temp[] = new String[nDimensions];
        int tempInt;
        Random random = new Random();
        for (int i = nEntries - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            for (int j = 0; j < nDimensions; j++ ) {
                temp[j] = entryMap[index][j];

            }
            tempInt = audioFiles[index];

            for (int j = 0; j < nDimensions; j++) {
                entryMap[index][j] = entryMap[i][j];
            }
            audioFiles[index] = audioFiles[i];

            for (int j = 0; j < nDimensions; j++) {
                entryMap[i][j] = temp[j];
            }
            audioFiles[i] = tempInt;

        }
    }

    // returns entry from target language array at index
    public String getTargetLanguageEntry(int index) {
        if (index < 0 || index >= nEntries) {
            throw new RuntimeException("Invalid index");
        }
        return entryMap[index][targetLanguageIndex];
    }

    // returns entry from known language array at index
    public String getKnownLanguageEntry(int index) {
        if (index < 0 || index >= nEntries) {
            throw new RuntimeException("Invalid index");
        }
        return entryMap[index][knownLanguageIndex];
    }

    // returns entry from capital target letters array at index
    public String getTargetCapitalLetterEntry(int index) {
        if (index < 0 || index >= nEntries) {
            throw new RuntimeException("Invalid index");
        }
        return entryMap[index][targetCapitalLettersIndex];
    }

    // returns entry from audio file array at index
    public int getAudioFileEntry(int index) {
        if (index < 0 || index >= nEntries) {
            throw new RuntimeException("Invalid index");
        }
        return audioFiles[index];
    }

}
