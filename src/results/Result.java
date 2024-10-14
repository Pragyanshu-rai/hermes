package src.results;

import java.util.Vector;

import src.log.Color;

public class Result {

    private String fileName;

    private Vector<String> resultSet;

    private boolean shouldLogFileName;

    public Result() {
        this.fileName = "";
        this.resultSet = new Vector<String>();
    }

    public Result(String fileName, boolean shouldLogFileName, boolean needsColor) {
        this();
        this.shouldLogFileName = shouldLogFileName;
        this.fileName = shouldLogFileName? this.addColorIf(fileName, needsColor) : "" ;
    }

    private String addColorIf(String fileName, boolean needsColor) {

        if(needsColor) {
            fileName = Color.addPurple(fileName);
        }
        return fileName;
    }

    public void add(int lineNumber, String line) {
        this.resultSet.add((new ResultSet(lineNumber, line)).toString());
    }

    @Override
    public String toString() {
        return (this.shouldLogFileName ? this.fileName+"\n" : "")+String.join("\n", this.resultSet);
    }
}
