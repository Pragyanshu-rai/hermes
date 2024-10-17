package src.argUtils;

public class Options {

    private final static String help = "[hH]";

    private final static String level = "[lL]";

    private final static String color = "[cC]";

    private final static String wordWrap = "[wW]";

    private final static String recursive = "[rR]";

    private final static String caseIgnore = "[iI]";

    private final static String performance = "[pP]";

    private boolean hasHelp;

    private boolean hasLevel;

    private boolean hasColor;

    private boolean isWordWrapped;

    private boolean isRecursiveSearch;

    private boolean isCaseInsensitive;

    // un-implemented
    private boolean shouldLogFileName;

    private boolean needsPerformanceLog;

    public Options() {
        this.hasHelp = false;
        this.hasLevel = false;
        this.hasColor = false;
        this.isWordWrapped = false;
        this.isRecursiveSearch = false;
        this.isCaseInsensitive = false;
        this.shouldLogFileName = true;
        this.needsPerformanceLog = false;
    }

    public Options(String optionsString) {
        this();
        this.setOptionFlags(optionsString);
    }

    private void setOptionFlags(String optionsString) {

        for(char symbol: optionsString.toCharArray()) {

            if(String.valueOf(symbol).matches(Options.help)) {
                this.hasHelp = true;
            } else if (String.valueOf(symbol).matches(Options.level)) {
                this.hasLevel = true;
            } else if (String.valueOf(symbol).matches(Options.color)) {
                this.hasColor = true;
            } else if (String.valueOf(symbol).matches(Options.wordWrap)) {
                this.isWordWrapped = true;
            }  else if (String.valueOf(symbol).matches(Options.caseIgnore)) {
                this.isCaseInsensitive = true;
            } else if (String.valueOf(symbol).matches(Options.recursive)) {
                this.isRecursiveSearch = true;
            } else if (String.valueOf(symbol).matches(Options.performance)) {
                this.needsPerformanceLog = true;
            }
        }
    }

    public boolean hasHelp() {
        return hasHelp;
    }

    public boolean hasLevel() {
        return hasLevel;
    }

    public boolean hasColor() {
        return hasColor;
    }

    public boolean isWordWrapped() {
        return isWordWrapped;
    }

    public boolean isRecursiveSearch() {
        return isRecursiveSearch;
    }

    public boolean isCaseInsensitive() {
        return isCaseInsensitive;
    }

    public boolean needsPerformanceLog() {
        return needsPerformanceLog;
    }

    public boolean isShouldLogFileName() {
        return shouldLogFileName;
    }
}