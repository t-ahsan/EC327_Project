package com.example.yana.alphabetter;

/**
 * Created by yana on 12/10/2017.
 *
 * Stores data for learn/quiz modes in Armenian
 */

public class ArmenianLetterMap extends GenericLetterMap {
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

    // constructor
    public ArmenianLetterMap() {
        super();
        targetLanguageAlphabetName = "Armenian";
        targetLanguageName = "Armenian";
        nEntries = 39;
        entryMap = new String[nEntries][nDimensions];
        for (int i = 0; i < nEntries; i++) {
            entryMap[i][targetLanguageIndex] = armenianLetters[i];
            entryMap[i][knownLanguageIndex] = transliteratedArmenianLetters[i];
            entryMap[i][targetCapitalLettersIndex] = capitalArmenianLetters[i];
        }

        //audioFiles = armenianAudioFiles;
    }


}
