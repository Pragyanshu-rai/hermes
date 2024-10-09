package src.log;

public class Message {

    public final static String helpArg =
            "\n"+
                    "Usage: look [OPTIONS] [FILE_PATH] [PATTERN] \n" +
                    "Search for Patterns in Files. \n"+
                    "Example: look -w -i 'error' /path/to/logs\n" +
                    "\n" +
                    "Options:\n" +
                    "  -w, -W          Search for patterns recursively in directories.\n" +
                    "  -i, -I          Perform case-insensitive pattern matching.\n" +
                    "  -c, -C          Will color code the output.\n" +
                    "  -l, -L          Can specify the depth level for the seach by passing a number in the argumet\n\t\twith this flag set.\n" +
                    "  -r, -R          Will run a recursive search down from the give path, this Supersedes depth level flag.\n" +
                    "  -p, -P          Will log the performance metric for the search session.\n" +
                    "  -h, -H          Show this help message and exit.\n" +
                    "\n" +
                    "Exit status is 0 if any line is selected, 1 otherwise; \n\n"+
                    "Report Bugs to: http://localhost:3000\n"+
                    "Start finding patterns now and streamline your workflow!";

    public final static String noArg =
            "\n"+
                    "Usage: look [OPTION] [PATTERN] [FILE]\n" +
                    "Try 'look -h' for more information.\n";
}