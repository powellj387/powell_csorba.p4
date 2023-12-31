//@author Julian Powell and Alex Csorba
package markov;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Milestone1 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java markov.Milestone1 <corpus_path>");
            System.exit(1);
        }

        String corpusPath = args[0];
        Path path = Paths.get(corpusPath);

        try {
            // Break the corpus into sentences
            List<String> sentences = LexicalParser.breakFileIntoSentences(path);

            // Count the number of sentences
            int numSentences = sentences.size();

            // Tokenize each sentence and collect unique tokens
            ArrayList<String> uniqueTokens = new ArrayList<>();
            for (String sentence : sentences) {
                List<String> tokens = LexicalParser.tokenizeSentence(sentence);

                for(String token : tokens) {
                    //ensures there are no repeat tokens
                    if(!uniqueTokens.contains(token)){
                        uniqueTokens.add(token);
                    }
                }
            }

            // Count the number of unique tokens
            int numUniqueTokens = uniqueTokens.size();

            // Print the results
            System.out.println("Number of sentences: " + numSentences);
            System.out.println("Number of unique tokens: " + numUniqueTokens);

        } catch (Exception e) {
            System.err.println("Error processing the corpus: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
