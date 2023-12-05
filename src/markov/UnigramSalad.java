package markov;

import java.util.*;

public class UnigramSalad {
    private Map<String, Map<String, Integer>> markovChain;
    private final Random rand;
    
    public UnigramSalad(int seed){
        this.markovChain = new HashMap<>();
        this.rand = new Random(seed);
        //put the first "special vertex in the map with an empty successor list
        markovChain.computeIfAbsent("", k-> new HashMap<>());
    }

    public void addOrderedTokens(java.util.Collection<java.lang.String> tokens) throws InvalidInputException{
        Set<String> sentenceEnders = new HashSet<>(Arrays.asList(".","!","?"));
        if(tokens == null||tokens.isEmpty()){
            throw new InvalidInputException("The list of n-grams must contain at least one n-gram");
        }
        //for every token check to see if in graph
        //if not in graph add to graph then create edge from token to next one
        String prevToken = "s";
        for(String token : tokens) {
            if (prevToken != null) {
                markovChain.computeIfAbsent(prevToken, k -> new HashMap<>());
                Map<String, Integer> currentToken = markovChain.get(prevToken);
                currentToken.put(token, currentToken.getOrDefault(token, 0)+1);
            }
            if(sentenceEnders.stream().anyMatch(token::endsWith)){
                prevToken = "s";
            }else{
                prevToken = token;
            }
        }
    }

    private String chooseNextToken(Map<String, Integer> nextTokens) {
        List<Map.Entry<String, Integer>> sortedTransitions = new ArrayList<>(nextTokens.entrySet());
        sortedTransitions.sort(Comparator.comparing(Map.Entry::getKey));
        int totalWeight = sortedTransitions.stream().mapToInt(Map.Entry::getValue).sum();
        int randomWeight = rand.nextInt(totalWeight) + 1;
        int accumulatedweight = 0;

        for (Map.Entry<String, Integer> entry : sortedTransitions) {

            accumulatedweight += entry.getValue();
            if (randomWeight <= accumulatedweight) {
                return entry.getKey();
            }
        }
        return null;
    }
    public java.lang.String generateSentence() throws InsufficientMarkovChainException{
       StringBuilder sentence = new StringBuilder();
       String currentToken = "s";

       while(true){
           Map<String, Integer> nextTokens = markovChain.get(currentToken);

           if(nextTokens==null||nextTokens.isEmpty()){
               break;
           }

           String nextToken = chooseNextToken(nextTokens);

           if(nextToken!=null){
               sentence.append(nextToken).append(" ");
               currentToken = nextToken;
           }
       }
       return sentence.toString().trim();
    }
}
