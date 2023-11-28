package markov;

public class UnigramSalad {

    public UnigramSalad(int seed){

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
