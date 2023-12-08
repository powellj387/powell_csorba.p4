//@author Julian Powell and Alex Csorba
package test;

import markov.InsufficientMarkovChainException;
import markov.InvalidInputException;
import markov.UnigramSalad;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class UnigramSaladTest {



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
    public void testGenerateSentence() throws InvalidInputException, InsufficientMarkovChainException {
        UnigramSalad salad = new UnigramSalad(123);
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "eat", "salad"));
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "play", "games"));
        String sentence = salad.generateSentence();

        // Check if the sentence is not empty
        assertNotEquals("", sentence);

        // Check if the sentence starts with "I"
        assertTrue(sentence.startsWith("I"));
    }
}