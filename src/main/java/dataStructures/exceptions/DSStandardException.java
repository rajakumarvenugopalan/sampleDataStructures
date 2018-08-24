package dataStructures.exceptions;

public class DSStandardException extends Exception {

    public DSStandardException() {
        super();
    }

    public DSStandardException(String message) {
        super(message);
    }

    public DSStandardException(String message, Throwable cause) {
        super(message, cause);
    }

}
