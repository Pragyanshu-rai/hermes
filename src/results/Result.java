package src.results;

import java.util.Vector;

import src.log.Colors;

public class Result {

    private String fileName;

    private Vector<String> resultSet;

    public Result() {
        this.fileName = "";
        this.resultSet = new Vector<String>();
    }

    public Result(String fileName, boolean needsColor) {
        this();
        this.fileName = fileName;
    }

    private String addColorIf(String fileName, boolean needsColor) {

        if(needsColor) {
            fileName = Color.PURPLE + fileName + Color.RESET;
        }
        return fileName;
    }

    public void add(int lineNumber, String line) {
        this.resultSet.add((new ResultSet(lineNumber, line)).toString());
    }

    @Override
    public String toString() {
        return this.fileName+"\n"+String.join("\n", this.resultSet);
    }
}
