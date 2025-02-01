package phi;

/**
 * Custom exception class for handling errors specific to the Phi application.
 * This exception is thrown when there is a problem related to the tasks, input, or commands.
 */
public class PhiException extends Exception {

    /**
     * Constructs a new PhiException with the specified detail message.
     *
     * @param message The detail message to describe the exception.
     */
    public PhiException(String message) {
        super(message);
    }
}
