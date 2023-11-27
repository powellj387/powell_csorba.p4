package markov;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class LexicalParser {
    public static java.util.List<java.lang.String> breakFileIntoSentences(java.nio.file.Path path) throws java.io.IOException{
        try{
            //read the file in and create a new instance of the file class using the path provided
            String file = new String(Files.readAllBytes(path));
            //split the created file based on sentence ending punctuation
            String[] sentences = file.split("(?<=[.!?])");
            //create the list of sentences that will be returned
            List<String> trueSentences = new ArrayList<>();
            //populate said list with the sentences created based on the splitting
            for(String sentence: sentences){
                if(!sentence.trim().isEmpty()){
                    trueSentences.add(sentence.trim());
                }
            }
            //return the sentences
            return trueSentences;
            //if for whatever reason there is an error, throw an IO exception
        }catch (IOException e){
            e.printStackTrace();
            throw new IOException();
        }
    }

    public static java.util.List<java.lang.String> tokenizeSentence(java.lang.String input){
        //Split the input up depending on token ending criteria
        String[] tokens = input.split(" ");
        //create list of tokens to be returned
        List<String> trueTokens = new ArrayList<>();
        //populate said list with the tokens created based on splitting
        for(String token: tokens){
            if(!token.trim().isEmpty()&&!trueTokens.contains(token)){
                trueTokens.add(token.trim());
                System.out.println(token);
            }
        }
        //return the tokens
        return trueTokens;
    }

}
