
package markov;

import java.util.*;

public class NGramSalad {
    private final Map<NGram, List<String>> markovChain = new HashMap<>();
    private final Random rand;
    private final int n;

    public NGramSalad(int n, int seed) {
        this.n = n;
        this.rand = new Random(seed);
    }

    public void addOrderedTokens(Collection<String> tokens) throws InvalidInputException {
        if (tokens.isEmpty()) {
            throw new InvalidInputException("The list of tokens must contain at least one token");
        }
        List<String> tokenList = new ArrayList<>(tokens);
        for (int i = 0; i < tokenList.size(); i++) {
            NGram nGram = new NGram(n);
            for (int j = i; j < i + n && j < tokenList.size(); j++) {
                nGram = nGram.add(tokenList.get(j));
                if (nGram.size() == n) {
                    NGram key = new NGram(n);
                    for (String gram : nGram.extractTokens().subList(0, n - 1)) {
                        key = key.add(gram);
                    }
                    markovChain.putIfAbsent(key, new ArrayList<>());
                    markovChain.get(key).add(nGram.last());
                }
            }
        }
    }

    public String generateSentence() throws InsufficientMarkovChainException {
        if (markovChain.isEmpty()) {
            throw new InsufficientMarkovChainException("Markov Chain is empty");
        }
        List<String> sentence = new ArrayList<>();
        NGram currentNGram = new NGram(n);
        for (int i = 0; i < 100; i++) {
            while (markovChain.containsKey(currentNGram)) {
                List<String> nextTokens = markovChain.get(currentNGram);
                if (nextTokens.isEmpty()) {
                    break;
                }
                int nextTokenIndex = rand.nextInt(nextTokens.size());
                String nextToken = nextTokens.get(nextTokenIndex);
                sentence.add(nextToken);
                currentNGram = currentNGram.add(nextToken);
            }
            String generatedSentence = String.join(" ", sentence);
            NGram sentenceNGram = new NGram(n);
            for (String token : LexicalParser.tokenizeSentence(generatedSentence)) {
                sentenceNGram = sentenceNGram.add(token);
            }
            if (!markovChain.containsKey(sentenceNGram)) {
                return generatedSentence;
            }
            sentence.clear();
        }
        throw new InsufficientMarkovChainException("A non-corpus sentence cannot be generated");
    }
}