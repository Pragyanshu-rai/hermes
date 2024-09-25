package src.results;

public class ResultSet {
    private String line;
    private int lineNumber;

    public ResultSet(int lineNumber, String line) {
        this.line = line;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "    - "+this.lineNumber+": "+this.line;
    }
}
