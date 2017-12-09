package com.example.yana.alphabetter;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yana on 11/24/2017.
 */

public class GenericLetterMap {
    // number of rows in entryMap
    public int nEntries;

    // the number of columns in entryMap
    public final int nDimensions = 2;

    // array that holds all question/answer pairs. First column is entries from the target language
    // the user is learning, and the second column is corresponding entries from the known language
    // (presumably English)
    protected String entryMap[][] = new String[nEntries][nDimensions];

    protected final int targetLanguageIndex = 0;

    protected final int knownLanguageIndex = 1;

    public String targetLanguageAlphabetName;
    public String knownLanguageAlphabetName = "Latin";
    public String targetLanguageName;
    public String knownLanguageName = "English";


    // shuffle all entries randomly, while still maintaining same indices for corresponding
    // target/known language pairs
    public void shuffleEntries() {
        int index;
        String temp[] = new String[nDimensions];
        Random random = new Random();
        for (int i = nEntries - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            for (int j = 0; j < nDimensions; j++ ) {
                temp[j] = entryMap[index][j];
            }

            for (int j = 0; j < nDimensions; j++) {
                entryMap[index][j] = entryMap[i][j];
            }

            for (int j = 0; j < nDimensions; j++) {
                entryMap[i][j] = temp[j];
            }

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

}
