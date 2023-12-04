package markov;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Milestone2 {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Usage: java markov.Milestone2 <corpus_path> <seed> <num_sentences>");
            System.exit(1);
        }

        String corpusPath = args[0];
        String seed = args[1];
        int numSentences = Integer.parseInt(args[2]);
        Path path = Paths.get(corpusPath);

        try {
            // Break the corpus into sentences
            List<String> sentences = LexicalParser.breakFileIntoSentences(path);

            // Initialize a random number generator with the seed
            Random rng = new Random(Long.parseLong(seed));

            // Generate the requested number of sentences
            for (int i = 0; i < numSentences; i++) {
                // Select a random sentence from the corpus
                String sentence = sentences.get(rng.nextInt(sentences.size()));

                // Print the sentence
                System.out.println(sentence);
            }

        } catch (Exception e) {
            System.err.println("Error processing the corpus: " + e.getMessage());
            e.printStackTrace();
        }
    }
}