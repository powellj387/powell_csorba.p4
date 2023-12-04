package test;

import markov.InsufficientMarkovChainException;
import markov.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import markov.UnigramSalad;

public class UnigramSaladTest {
    @Test
    public void testValidInputWithDifferentTokens() throws InvalidInputException, InsufficientMarkovChainException {
        UnigramSalad salad = new UnigramSalad(456);
        salad.addOrderedTokens(Arrays.asList("apple", "banana", "cherry", "banana", "apple"));
        String generatedSentence = salad.generateSentence();
        assertNotNull(generatedSentence);
        assertTrue(generatedSentence.contains("apple"));
        assertTrue(generatedSentence.contains("banana"));
        assertTrue(generatedSentence.contains("cherry"));
    }

    @Test
    public void testEmptyMarkovChainWithDifferentSeed() {
        UnigramSalad salad = new UnigramSalad(789);
        try {
            String generatedSentence = salad.generateSentence();
            // The Markov chain is empty, so the generated sentence should be empty
            assertEquals("", generatedSentence);
        } catch (InsufficientMarkovChainException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testSingleTokenMarkovChainWithDifferentToken() throws InvalidInputException, InsufficientMarkovChainException {
        UnigramSalad salad = new UnigramSalad(101);
        salad.addOrderedTokens(Arrays.asList("orange"));
        String generatedSentence = salad.generateSentence();
        assertNotNull(generatedSentence);
        assertEquals("orange", generatedSentence.trim());
    }



    @Test
    public void testGenerateSentence() throws Exception, InvalidInputException, InsufficientMarkovChainException {
        UnigramSalad salad = new UnigramSalad(123);
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "eat", "salad"));
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "play", "games"));
        String sentence = salad.generateSentence();
        // Check if the sentence is not empty
        assertNotEquals("", sentence);
        // Check if the sentence starts with "I"
        assertEquals("I", sentence.split(" ")[0]);
    }
}