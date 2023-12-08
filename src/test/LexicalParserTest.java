//@author Julian Powell and Alex Csorba
package test;
import org.junit.jupiter.api.Test;
import markov.LexicalParser;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexicalParserTest {

    @Test
    public void breakFileIntoSentences_invalidFile_throwsIOException() {
        // Define a non-existent file path to simulate an invalid file
        Path invalidFilePath = Paths.get("nonexistentfile.txt");

        // Call the method under test within a lambda to capture the exception
        assertThrows(IOException.class, () -> LexicalParser.breakFileIntoSentences(invalidFilePath));
    }

    @Test
    public void tokenizeSentenceCorrectValue() {
        // Define a test input
        String testInput = "This is a test sentence.";

        // Call the method under test
        List<String> tokens = LexicalParser.tokenizeSentence(testInput);

        // Assert the expected result
        assertEquals(5, tokens.size());  // Adjust the expected size based on your test input
        // Add more assertions based on your specific test input content and expectations
    }

    @Test
    public void tokenizeSentenceWithMultipleOfTheSameCorrectValue() {
        // Test sentence
        String testInput = "This This This This This is is is a test test test sentence sentence sentence sentence.";

        // Call the method under test
        List<String> tokens = LexicalParser.tokenizeSentence(testInput);
        ArrayList<String> uniqueTokens = new ArrayList<>();
        for(String token : tokens) {
            //ensures there are no repeat tokens
            if(!uniqueTokens.contains(token)){
                uniqueTokens.add(token);
            }
        }
        // Assert the expected result
        assertEquals(6, uniqueTokens.size());  // Adjust the expected size based on your test input
        // Add more assertions based on your specific test input content and expectations
    }

    @Test
    public void tokenizeWackySentenceCorrectValue() {
        // Define a test input
        String testInput = "This is a test sentence.  y uo m(). $$what is this// 'f' [atrick]";

        // Call the method under test
        List<String> tokens = LexicalParser.tokenizeSentence(testInput);

        // Assert the expected result
        assertEquals(13, tokens.size());  // Adjust the expected size based on your test input
        // Add more assertions based on your specific test input content and expectations
    }

    @Test
    public void breakIntoSentencesCorrectValue() throws IOException {
        // Call the method under test
        List<String> tokens = LexicalParser.breakFileIntoSentences(Paths.get("resources/sentenceTest.txt"));

        // Assert the expected result
        assertEquals(5, tokens.size());  // Adjust the expected size based on your test input
        // Add more assertions based on your specific test input content and expectations
    }
}
