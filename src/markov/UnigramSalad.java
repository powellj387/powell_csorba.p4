package markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnigramSalad {
    private Map<String, List<String>> markovChain = new HashMap<>();
    private ArrayList<String> sVertexSuccessors = new ArrayList<>();
    private int randSeed;
    
    public UnigramSalad(int seed){
        randSeed = seed;
        //put the first "special vertex in the map with an empty successor list
        markovChain.put("s",sVertexSuccessors);
    }

    public void addOrderedTokens(java.util.Collection<java.lang.String> tokens) throws InvalidInputException{
        //for every token check to see if in graph

        //if not in graph add to graph then create edge from token to next one
    }

    public java.lang.String generateSentence() throws InsufficientMarkovChainException{
        //use lottery algo for randomness
        //use dijkstra's algo for weighted traversal
    }
}
