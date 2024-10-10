package src.main;

import src.results.Result;
import src.results.ResultCounter;

import java.util.Vector;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

class Finder implements Runnable {

    private Result result;

    private String target;

    private String fileName;

    private static Vector<String> resultList;

    static {
        Finder.resultList = new Vector<String>();
    }

    public Finder(String fileName, String target, boolean needsColor) {
        this.fileName = fileName;
        this.target = target;
        this.result = new Result(fileName, needsColor);
    }

    private static synchronized void log(Result result) {
        ResultCounter.increment();
        resultList.add(result.toString());
    }

    public void show() {
        System.out.println(String.join("\n", resultList));
    }

    private void find(boolean shouldLog) throws IOException{
        BufferedReader file = null;

        try {
            String line;
            int lineNumber = 1;
            file = new BufferedReader(new FileReader(this.fileName));
            boolean found = false;

            while((line = file.readLine()) != null) {

                if(line.matches(this.target)) {
                    found = true;
                    this.result.add(lineNumber, line);
                }
                lineNumber++;
            }
            if (found) {
                log(this.result);

                if (shouldLog) {
                    System.out.println(String.join("\n", this.result.toString()));
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(this.fileName+" NOT FOUND");
        } finally {

            if (file != null) {
                file.close();
            }
        }
    }

    public void run() {
        try {
            this.find(true);
        } catch (Exception e) {
            System.out.print("Exception in RUN");
        }
    }
}
