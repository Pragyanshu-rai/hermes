package src.exceptions;

public class HelpArgException extends Exception{

    private final static String message =
            "\n"+
            "Usage: look [OPTIONS] [FILE_PATH] [PATTERN] \n" +
            "Search for Patterns in Files. \n"+
            "Example: look -w -i 'error' /path/to/logs\n" +
            "\n" +
            "Options:\n" +
            "  -w, -W          Search for patterns recursively in directories.\n" +
            "  -i, -I          Perform case-insensitive pattern matching.\n" +
            "  -h, -H          Show this help message and exit.\n" +
            "\n" +
            "Exit status is 0 if any line is selected, 1 otherwise; \n\n"+
            "Report Bugs to: http://localhost:3000\n"+
            "Start finding patterns now and streamline your workflow!";


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
