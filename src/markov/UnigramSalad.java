package markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class UnigramSalad {
    // Map to store the Markov chain
    private Map<String, List<String>> markovChain = new HashMap<>();
    // List to store successors of the special vertex "s"
    private ArrayList<String> sVertexSuccessors = new ArrayList<>();
    // Seed for random number generator
    private int randSeed;
    // Random number generator
    private Random rand;

    // Constructor
    public UnigramSalad(int seed){
        randSeed = seed;
        // Initialize the random number generator with the seed
        this.rand = new Random(randSeed);
        // Put the first "special vertex in the map with an empty successor list
        markovChain.put("s",sVertexSuccessors);
    }

    // Method to add ordered tokens to the Markov chain
    public void addOrderedTokens(java.util.Collection<java.lang.String> tokens) throws InvalidInputException{
        // For every token check to see if in graph
        // If not in graph add to graph then create edge from token to next one
        String prevToken = "s";
        for(String token : tokens){
            if(!markovChain.containsKey(prevToken)){
                markovChain.put(prevToken, new ArrayList<>());
            }
            markovChain.get(prevToken).add(token);
            prevToken = token;
        }
    }

    // Method to generate a sentence using the Markov chain
    public java.lang.String generateSentence() throws InsufficientMarkovChainException{
        // Use lottery algo for randomness
        // Use Dijkstra's algo for weighted traversal
        if(markovChain.isEmpty()){
            throw new InsufficientMarkovChainException("Markov Chain is empty");
        }
        String currentToken = "s";
        StringBuilder sentence = new StringBuilder();
        while(markovChain.containsKey(currentToken)){
            List<String> nextTokens = markovChain.get(currentToken);
            if(nextTokens.isEmpty()){
                break;
            }
            int nextTokenIndex = rand.nextInt(nextTokens.size());
            currentToken = nextTokens.get(nextTokenIndex);
            sentence.append(currentToken).append(" ");
        }
        return sentence.toString().trim();
    }
}