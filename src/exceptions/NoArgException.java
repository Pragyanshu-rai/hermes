package src.exceptions;

public class NoArgException extends Exception{

    private final static String message =
            "\n"+
            "Usage: look [OPTION] [PATTERN] [FILE]\n" +
            "Try 'look -h' for more information.\n";

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
