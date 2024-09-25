package src.argUtils;

public class TransformTarget {

    private static String wordMatch;

    private static String ignoreCase;

    private String options;


    public TransformTarget() {
        wordMatch = "^.*[wW].*$";
        ignoreCase = "^.*[iI].*$";
    }

    public void addOptions(String options) {
        this.options = options;
    }

    public String transform(String options, String target) {
        this.addOptions(options);

        if (this.options.matches(wordMatch)) {
                target = target+"\\b";
        }
        target = "^.*"+target+".*$";

        if (this.options.matches(ignoreCase)) {
            target = "(?i)"+target;
        }
        return target;
    }
}
