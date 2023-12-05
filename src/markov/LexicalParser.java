package markov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public abstract class LexicalParser {
    public static List<String> breakFileIntoSentences(Path path) throws IOException{
        try{
            //read the file in and create a new instance of the file class using the path provided
            String file = new String(Files.readAllBytes(path));
            
            //split the created file based on sentence ending punctuation
            String[] sentences = file.split("(?<=[.])|(?<=[?])|(?<=!)");

            //get rid of any sentences that don't match the rules
            Pattern sentencePattern = Pattern.compile("\\w");

            //create the list of sentences that will be returned
            String[] trueSentences = Arrays.stream(sentences)
                    .filter(sentence ->sentencePattern.matcher(sentence).find())
                    .filter(sentence -> sentence.endsWith(".")||sentence.endsWith("?")||sentence.endsWith("!"))
                    .toArray(String[]::new);

            //return the sentences
            return List.of(trueSentences);

            //if for whatever reason there is an error, throw an IO exception
        }catch (IOException e){
            e.printStackTrace();
            throw new IOException();
        }
    }

    public static java.util.List<java.lang.String> tokenizeSentence(java.lang.String input){
        String trueInput = input.trim();
        //Split the input up depending on token ending criteria
        String[] tokens = trueInput.split("\\s+");

        /*//Ensure that every token contains at least 1 \w character
        Pattern tokenPattern = Pattern.compile("\\w");
*/
        //create list of tokens to be returned
        List<String> trueTokens = new ArrayList<>();
        //populate said list with the tokens created based on splitting
        for(String token: tokens){
            if(token.matches(".*\\w.*")){
                trueTokens.add(token);
            }
        }
        //return the tokens
        return trueTokens;
    }
}