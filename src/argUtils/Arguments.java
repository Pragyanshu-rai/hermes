package src.argUtils;

public class Arguments {

    private int level;

    private String src;

    private String pattern;

    private Options options;

    public Arguments(int level, String src, String pattern, Options options) {
        this.src = src;
        this.level = level;
        this.pattern = pattern;
        this.options = options;
    }

    public int getLevel() {
        return level;
    }

    public String getSrc() {
        return src;
    }

    public String getPattern() {
        return pattern;
    }

    public Options getOptions() {
        return options;
    }
}