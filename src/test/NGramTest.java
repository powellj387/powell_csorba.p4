//@author Julian Powell and Alex Csorba
package test;

import markov.NGram;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NGramTest {

    @Test
    public void testConstructor() {
        NGram ngram = new NGram(3);
        assertEquals(0, ngram.size());
    }

    @Test
    public void testAdd() {
        NGram ngram = new NGram(3);
        ngram.add("apple");
        ngram.add("banana");
        assertEquals(2, ngram.size());
        assertEquals("banana", ngram.last());
    }

    @Test
    public void testSize() {
        NGram ngram = new NGram(3);
        ngram.add("apple");
        ngram.add("banana");
        ngram.add("cherry");
        ngram.add("date");
        assertEquals(3, ngram.size());
    }

    @Test
    public void testExtractTokens() {
        NGram ngram = new NGram(3);
        ngram.add("apple");
        ngram.add("banana");
        ngram.add("cherry");
        assertEquals(Arrays.asList("apple", "banana", "cherry"), ngram.extractTokens());
    }

    @Test
    public void testLast() {
        NGram ngram = new NGram(3);
        ngram.add("apple");
        ngram.add("banana");
        ngram.add("cherry");
        assertEquals("cherry", ngram.last());
    }

    @Test
    public void testEquals() {
        NGram ngram1 = new NGram(3);
        ngram1.add("apple");
        ngram1.add("banana");
        ngram1.add("cherry");

        NGram ngram2 = new NGram(3);
        ngram2.add("apple");
        ngram2.add("banana");
        ngram2.add("cherry");

        assertTrue(ngram1.equals(ngram2));
    }

    @Test
    public void testHashCode() {
        NGram ngram1 = new NGram(3);
        ngram1.add("apple");
        ngram1.add("banana");
        ngram1.add("cherry");

        NGram ngram2 = new NGram(3);
        ngram2.add("apple");
        ngram2.add("banana");
        ngram2.add("cherry");

        assertEquals(ngram1.hashCode(), ngram2.hashCode());
    }

    @Test
    public void testToString() {
        NGram ngram = new NGram(3);
        ngram.add("apple");
        ngram.add("banana");
        ngram.add("cherry");
        assertEquals("apple banana cherry", ngram.toString());
    }

    @Test
    public void testCompareTo() {
        NGram ngram1 = new NGram(3);
        ngram1.add("apple");
        ngram1.add("banana");
        ngram1.add("cherry");

        NGram ngram2 = new NGram(3);
        ngram2.add("apple");
        ngram2.add("banana");
        ngram2.add("date");

        assertTrue(ngram1.compareTo(ngram2) < 0);
    }
}