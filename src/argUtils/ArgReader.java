package src.argUtils;

import src.exceptions.*;

public class ArgReader {
    private String[] arguments;

    private final static String helpArg = "-.*[h,H].*";

    public ArgReader(String[] args) {
        this.arguments = args;
    }

    private void validate() throws NoArgException, HelpArgException {

        if (this.arguments == null || this.arguments.length == 0) {
            throw new NoArgException();
        }

        for (String arg: this.arguments) {

            if (arg.matches(helpArg)) {
                throw new HelpArgException();
            }
        }
    }

    private String[] extractSourceAndTarget(){
        int index = 0;
        String[] sourceAndTarget = new String[2];

        for (String arg : arguments) {

            if (index == 2) {
                break;
            }

            if (!arg.matches("^-.*$")) {
                sourceAndTarget[index++] = arg;
            }
        }

        if (index == 1) {
            sourceAndTarget[1] = sourceAndTarget[0];
            sourceAndTarget[0] = ".";
        } else if (index < 1) {
            sourceAndTarget[0] = null;
            sourceAndTarget[1] = null;
        }
        return sourceAndTarget;
    }

    private String extractOptions() {
        StringBuffer allOptions = new StringBuffer();

        for (String arg : arguments) {

            if (arg.matches("^-.*$")) {
                allOptions.append(arg.substring(1));
            }
        }

        return allOptions.toString();
    }

    public ParseArg parse() throws NoArgException, HelpArgException{
        this.validate();
        String option = this.extractOptions();
        String[] sourceAndTarget = this.extractSourceAndTarget();
        return new ParseArg(option, sourceAndTarget[0], sourceAndTarget[1]);
    }
}
