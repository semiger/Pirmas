package com.example.pirmas;

public class StringCountCalculator {
    public static int GetWordCount (String text) {
        if (text.isEmpty())
            return 0;

        return text.split("[ ,.:;]").length;
    }
    public static int GetSymbolCount (String text) {
        return text.length();
    }
}
