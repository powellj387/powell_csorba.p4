package markov;

import java.util.ArrayList;
import java.util.List;

public class NGram implements Comparable<NGram> {
    public static final char DELIMITER = '|';
    private final List<String> grams;
    private final int n;

    public NGram(int n) {
        this.n = n;
        this.grams = new ArrayList<>();
    }

    public NGram add(String gram) {
        if (this.grams.size() == n) {
            this.grams.remove(0);
        }
        this.grams.add(gram);
        return this;
    }

    public int size() {
        return this.grams.size();
    }

    public List<String> extractTokens() {
        return new ArrayList<>(this.grams);
    }

    public String last() {
        return this.grams.get(this.grams.size() - 1);
    }


    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        NGram nGram = (NGram) otherObject;
        return grams.equals(nGram.grams);
    }


    public int hashCode() {
        return grams.hashCode();
    }


    public String toString() {
        return String.join(String.valueOf(DELIMITER), this.grams);
    }


    public int compareTo(NGram o) {
        return this.toString().compareTo(o.toString());
    }
}