package src.exceptions;

import src.log.Message;

public class NoArgException extends Exception{

    private static String message = Message.noArg;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
