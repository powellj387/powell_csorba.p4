//@author Julian Powell and Alex Csorba
package markov;

public class InvalidInputException extends Throwable {
    public InvalidInputException(){}
    public InvalidInputException(String message) {
        super(message);
    }
}
