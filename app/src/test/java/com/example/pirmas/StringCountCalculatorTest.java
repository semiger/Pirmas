package com.example.pirmas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCountCalculatorTest {
    @Test
    public void GetSymbolCount_empty() {
        assertEquals(0, StringCountCalculator.GetSymbolCount(""));
    }

    @Test
    public void GetSymbolCount_10Symbols() {
        assertEquals(10, StringCountCalculator.GetSymbolCount("1234567890"));
    }

    @Test
    public void GetSymbolCount_SpacesOnly() {
        assertEquals(4, StringCountCalculator.GetSymbolCount("    "));
    }

    @Test
    public void GetSymbolCount_PunctuationOnly() {
        assertEquals(4, StringCountCalculator.GetSymbolCount(".,:;"));
    }
    @Test
    public void GetWordCount_empty() {
        assertEquals(0, StringCountCalculator.GetWordCount(""));
    }

    @Test
    public void GetWordCount_2Words() {
        assertEquals(2, StringCountCalculator.GetWordCount("Lorem Ipsum"));
    }

    @Test
    public void GetWordCount_4Words() {
        assertEquals(4, StringCountCalculator.GetWordCount("Lorem ipsum dolor sit"));
    }

    @Test
    public void GetWordCount_4WordsWithPunctuation() {
        assertEquals(4, StringCountCalculator.GetWordCount("Lorem ipsum dolor sit."));
    }

    @Test
    public void GetWordCount_SpacesOnly() {
        assertEquals(0, StringCountCalculator.GetWordCount("    "));
    }

    @Test
    public void GetWordCount_PunctuationOnly() {
        assertEquals(0, StringCountCalculator.GetWordCount(".,:;"));
    }
}
