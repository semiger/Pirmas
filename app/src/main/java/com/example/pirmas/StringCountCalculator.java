package com.example.pirmas;

public class StringCountCalculator {
    public static int GetWordCount (String tekstas) {
        return tekstas.split(" ").length;
    }
    public static int GetSymbolCount (String tekstas) {
        return tekstas.length();
    }
}
