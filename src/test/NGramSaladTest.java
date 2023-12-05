package test;

import markov.InsufficientMarkovChainException;
import markov.InvalidInputException;
import markov.NGramSalad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NGramSaladTest {


    @Test
    public void testGenerateSentence() throws InvalidInputException, InsufficientMarkovChainException {
        NGramSalad salad = new NGramSalad(2, 123);
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "eat", "salad"));
        salad.addOrderedTokens(Arrays.asList("I", "like", "to", "play", "games"));
        String sentence = salad.generateSentence();

        // Check if the sentence is not null
        assertNotNull(sentence);

        // If the sentence is not empty, check if it starts with "I"
        if (!sentence.isEmpty()) {
            assertTrue(sentence.startsWith("I"));
        }
    }

    @Test
    public void testAddOrderedTokens_ValidInput() {
        NGramSalad salad = new NGramSalad(2, 123);
        List<String> tokens = Arrays.asList("This", "is", "a", "test");
        assertDoesNotThrow(() -> salad.addOrderedTokens(tokens));
    }

    @Test
    public void testAddOrderedTokens_EmptyList() {
        NGramSalad salad = new NGramSalad(2, 123);
        List<String> tokens = new ArrayList<>();
        assertThrows(InvalidInputException.class, () -> salad.addOrderedTokens(tokens));
    }

    @Test
    public void testGenerateSentence_EmptyMarkovChain() {
        NGramSalad salad = new NGramSalad(2, 123);
        assertThrows(InsufficientMarkovChainException.class, () -> salad.generateSentence());
    }


}