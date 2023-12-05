package markov;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Milestone2 {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Usage: java markov.Milestone2 <corpus_path> <seed> <num_sentences");
            System.exit(1);
        }

        String corpusPath = args[0];
        int seed = Integer.parseInt(args[1]);
        int numSentences = Integer.parseInt(args[2]);
        Path path = Paths.get(corpusPath);
        UnigramSalad UniSalad = new UnigramSalad(seed);

        try {
            // Break the corpus into sentences
            List<String> sentences = LexicalParser.breakFileIntoSentences(path);

            //break each sentence into its tokens and add them to the UnigramSalad
            ArrayList<String> uniqueTokens = new ArrayList<>();
            for (String sentence : sentences) {
                List<String> tokens = LexicalParser.tokenizeSentence(sentence);
                UniSalad.addOrderedTokens(tokens);
            }

            //print out necessary number of sentences
            for(int i=0;i<numSentences;i++) {
                System.out.println(UniSalad.generateSentence());
            }
        } catch (Exception e) {
            System.err.println("Error processing the corpus: " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidInputException | InsufficientMarkovChainException e) {
            throw new RuntimeException(e);
        }
    }
}
