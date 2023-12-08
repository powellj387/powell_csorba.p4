//@author Julian Powell and Alex Csorba
package markov;

import java.util.*;
import java.util.stream.Collectors;

public class UnigramSalad {
    private final Map<String, Map<String, Integer>> markovChain;
    private final Random rand;

    public UnigramSalad(int seed) {
        this.markovChain = new HashMap<>();
        this.rand = new Random(seed);
        markovChain.put("", new HashMap<>());  // Special vertex with an empty successor list
    }

    public void addOrderedTokens(Collection<String> tokens) throws InvalidInputException {
        Set<String> sentenceEnders = new HashSet<>(Arrays.asList(".", "!", "?"));
        if (tokens == null || tokens.isEmpty()) {
            throw new InvalidInputException("The list of tokens must contain at least one token");
        }

        String prevToken = "s";

        for (String currentToken : tokens) {
            markovChain.computeIfAbsent(prevToken, k -> new HashMap<>())
                    .merge(currentToken, 1, Integer::sum);

            prevToken = sentenceEnders.stream().anyMatch(currentToken::endsWith) ? "s" : currentToken;
        }
    }

    private String chooseNextToken(Map<String, Integer> nextTokens) {
        List<String> sortedTransitions = nextTokens.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        int totalWeight = sortedTransitions.stream().mapToInt(nextTokens::get).sum();
        int randomWeight = rand.nextInt(totalWeight) + 1;
        int tokenWeight = 0;
        String chosenToken = null;

        for (String token : sortedTransitions) {
            tokenWeight += nextTokens.get(token);
            if (randomWeight <= tokenWeight) {
                chosenToken = token;
                break;
            }
        }

        return chosenToken;
    }


    public String generateSentence() throws InsufficientMarkovChainException {
        StringBuilder sentence = new StringBuilder();
        String currentToken = "s";

        while (true) {
            Map<String, Integer> nextTokens = markovChain.get(currentToken);

            if (nextTokens == null || nextTokens.isEmpty()) {
                break;
            }

            String nextToken = chooseNextToken(nextTokens);

            if (nextToken != null) {
                sentence.append(nextToken).append(" ");
                currentToken = nextToken;
            }
        }

        return sentence.toString().trim();
    }
}

