package src.argUtils;

import java.util.Vector;

import src.exceptions.NoArgException;
import src.exceptions.HelpArgException;

public class ArgumentsTransform {

    private final static String isDigit = "^-?[0-9]*$";

    private final static String isOption = "^-[a-zA-Z]*$";

    private int level;

    private String src;

    private String pattern;

    private String[] args;

    private String options;

    private boolean hasLevel;

    private boolean needsHelp;

    private boolean isWordWrapped;

    private boolean caseInsensitive;

    {
        // setting level to a infinite
        this.level = -1;

        // setting src to the current folder
        this.src = ".";

        // setting pattern to an empty string
        this.pattern = new String();

        // setting options to an empty string
        this.options = new String();

        // setting all the flags to false
        this.hasLevel = false;

        this.needsHelp = false;

        this.isWordWrapped = false;

        this.caseInsensitive = false;
    }

    public ArgumentsTransform(String[] args) {
        this.args = args;
    }

    private void extractOptions() {
        StringBuffer options = new StringBuffer();

        for (String arg: args) {

            if (arg.matches(isOption)) {
                options.append(arg);
            }
        }
        this.optionsString = options.toString();
    }

    private void setOptionFlags() {
        this.extractOptions();

        for(char symbol: this.optionsString.toCharArray()) {

            if(symbol == 'h' || symbol == 'H') {
                this.needsHelp = true;
            } else if (symbol == 'i' || symbol == 'I') {
                this.caseInsensitive = true;
            } else if (symbol == 'w' || symbol == 'W') {
                this.isWordWrapped = true;
            } else if (symbol == 'l' || symbol == 'L') {
                this.hasLevel = true;
            }
        }
    }

    private void extractLevel() {

        for(String arg: args) {

            if(arg.matches(isDigit)) {
                this.level = Integer.parseInt(arg);
            }
        }
    }

    private void loadOptions() throws HelpArgException{
        this.setOptionFlags();

        if(this.needsHelp) {
            throw new HelpArgException();
        } if(this.hasLevel) {
            this.extractLevel();
        }
    }

    private void loadSourceAndPattern() throws NoArgException {
        Vector<String> result = new Vector<String>();

        for (String arg: args) {

            if ((!arg.matches(isOption)) && (!arg.matches(isDigit))) {
                result.add(arg);
            }
        }
        final int size = result.size();

        if (size == 0) {
            throw new NoArgException();
        } else if (size == 1) {
            this.pattern = result.get(0);
        } else {
            this.src = result.get(0);
            this.pattern = result.get(1);
        }
    }

    private void transformPattern() {

        if (this.isWordWrapped) {
            this.pattern += "\\b";
        }
        this.pattern = "^.*" + this.pattern + ".*$";

        if (this.caseInsensitive) {
            this.pattern = "(?i)" + this.pattern;
        }
    }

    private void parseArguments() throws NoArgException, HelpArgException {

        // if no arguments are found
        if (this.args == null || this.args.length == 0) {
            throw new NoArgException();
        }

        // extracting and loading the options
        this.loadOptions();

        // extracting source and pattern from the arguments
        this.loadSourceAndPattern();

        // transforming the pattern
        this.transformPattern();
    }

    public Arguments getArguments() throws NoArgException, HelpArgException {
        // parsing the arguments
        this.parseArguments();;

        // returning the Arguments object
        return new Arguments(this.level, this.src, this.pattern, this.optionsString);
    }
}