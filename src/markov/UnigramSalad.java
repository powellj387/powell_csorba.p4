package markov;

import java.util.*;

public class UnigramSalad {
    private Map<String, List<String>> markovChain = new HashMap<>();
    private ArrayList<String> sVertexSuccessors = new ArrayList<>();
    private int randSeed;
    private Random rand;
    
    public UnigramSalad(int seed){
        randSeed = seed;
        this.rand = new Random(randSeed);
        //put the first "special vertex in the map with an empty successor list
        markovChain.put("s",sVertexSuccessors);
    }

    public void addOrderedTokens(java.util.Collection<java.lang.String> tokens) throws InvalidInputException{
        //for every token check to see if in graph
        //if not in graph add to graph then create edge from token to next one
        String prevToken = "s";
        for(String token : tokens){
            if(!markovChain.containsKey(prevToken)){
                markovChain.put(prevToken, new ArrayList<>());
            }
            markovChain.get(prevToken).add(token);
            prevToken = token;
        }
    }

    public java.lang.String generateSentence() throws InsufficientMarkovChainException{
        //use lottery algo for randomness
        //use dijkstra's algo for weighted traversal
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
