package src.exceptions;

import src.log.Message;

public class HelpArgException extends Exception{

    private final static String message = Message.helpArg;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
