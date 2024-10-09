package src.argUtils;

public class Arguments {

    private int level;

    private String src;

    private String pattern;

    private String options;

    public Arguments(int level, String src, String pattern, String options) {
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

    public String getOptions() {
        return options;
    }
}