package com.example.yana.alphabetter;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yana on 11/24/2017.
 */

public abstract class GenericLetterMap {
    // number of entries
    public int nEntries;

    // array to hold all entries in target language
    protected String targetLanguageEntries[] = new String[nEntries];

    // array to hold all entries in known language (ie English)
    protected String knownLanguageEntries[] = new String[nEntries];

    public GenericLetterMap() {

    }

    // shuffle all entries randomly, while still maintaining same indices for corresponding
    // target/known language pairs
    public void shuffleEntries() {
        int index;
        String tTemp, kTemp;
        Random random = new Random();
        for (int i = nEntries - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            tTemp = targetLanguageEntries[index];
            kTemp = knownLanguageEntries[index];
            targetLanguageEntries[index] = targetLanguageEntries[i];
            knownLanguageEntries[index] = knownLanguageEntries[i];
            targetLanguageEntries[i] = tTemp;
            knownLanguageEntries[i] = kTemp;
        }
    }

    public String getTargetLanguageEntry(int index) {
        return targetLanguageEntries[index];
    }

    public String getKnownLanguageEntry(int index) {
        return knownLanguageEntries[index];
    }

    public int getEntryIndex(String entry) {
        int index = -1;
        boolean indexFound = false;
        for (int i = 0; i < nEntries; i++) {
            if(targetLanguageEntries[i].equals(entry) || knownLanguageEntries[i].equals(entry)) {
                index = i;
                indexFound = true;
            }
        }
        if (!indexFound) {
            throw new RuntimeException("Invalid entry");
        }

        return index;
    }

    public String getCorrectTargetLanguageAnswer(String knownLanguageEntry) {
        int correct_index = 0;
        for (int i = 0; i < nEntries; i++) {
            if (knownLanguageEntries[i].equals(knownLanguageEntry)) {
                correct_index = i;
            }
        }
        return targetLanguageEntries[correct_index];
    }

    public String getCorrectKnownLanguageAnswer(String targetLanguageEntry) {
        int correct_index = 0;
        for (int i = 0; i < nEntries; i++) {
            if (targetLanguageEntries[i].equals(targetLanguageEntry)) {
                correct_index = i;
            }
        }
        return knownLanguageEntries[correct_index];
    }
}
