//@author Julian Powell and Alex Csorba
package markov;

public class InsufficientMarkovChainException extends Throwable {
    public InsufficientMarkovChainException() {}
    public InsufficientMarkovChainException(String message){
        super(message);
    }
}
