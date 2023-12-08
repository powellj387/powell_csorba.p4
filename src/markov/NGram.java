//@author Julian Powell and Alex Csorba
package markov;

import java.util.ArrayList;
import java.util.List;

// Represents a sequence of 'n' tokens
public class NGram implements Comparable<NGram> {
    // This is the delimiter used when converting the NGram to a string.
    public static final char DELIMITER = '|';
    // This is the list of tokens in the NGram.
    private final List<String> grams;
    // This is the maximum number of tokens that can be stored in the NGram.
    private final int n;

    // Constructor that initializes 'n' and the list of grams
    public NGram(int n) {
        this.n = n;
        this.grams = new ArrayList<>();
    }

    // Adds a new token to the NGram. If the NGram already contains 'n' tokens, it removes the oldest token before adding the new one
    public NGram add(String gram) {
        if (this.grams.size() == n) {
            this.grams.remove(0);
        }
        this.grams.add(gram);
        return this;
    }

    // Returns the number of tokens in the NGram
    public int size() {
        return this.grams.size();
    }

    // Returns a new list containing all the tokens in the NGram
    public List<String> extractTokens() {
        return new ArrayList<>(this.grams);
    }

    // Returns the most recently added token
    public String last() {
        return this.grams.get(this.grams.size() - 1);
    }

    // Checks if another object is an NGram with the same tokens
    public boolean equals(Object otherObject) {
        boolean result = false;
        if (this == otherObject) {
            result = true;
        } else if (otherObject != null && getClass() == otherObject.getClass()) {
            NGram nGram = (NGram) otherObject;
            result = grams.equals(nGram.grams);
        }
        return result;
    }

    // Returns a hash code value for the NGram
    public int hashCode() {
        return grams.hashCode();
    }

    // Returns a string representation of the NGram, with tokens separated by a delimiter
    public String toString() {
        return String.join(" ", grams);
    }

    // Compares this NGram to another NGram based on their string representations
    public int compareTo(NGram o) {
        return this.toString().compareTo(o.toString());
    }
}