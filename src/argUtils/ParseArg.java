package src.argUtils;

public class ParseArg {

    private String source;

    private String target;

    private String options;

    public ParseArg(String options, String source, String target) {
        this.source = source;
        this.target = target;
        this.options = options;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
