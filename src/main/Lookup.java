package src.main;

import src.results.Result;
import src.results.ResultCounter;

import java.util.Vector;
import java.lang.Thread;
import java.lang.Runnable;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Lookup implements Runnable{

    private Result result;

    private String target;

    private boolean needsColor;

    private boolean lookupComplete;

    private boolean shouldLogFileName;

    {
        this.lookupComplete = false;
    }

    public Lookup(String target, boolean needsColor, boolean shouldLogFileName) {
        this.target = target;
        this.needsColor = needsColor;
        this.shouldLogFileName = shouldLogFileName;
    }

    private synchronized void find(String fileName) throws IOException{
        BufferedReader file = null;
        this.result = new Result(fileName, this.shouldLogFileName, this.needsColor);

        try {
            String line;
            int lineNumber = 1;
            boolean found = false;
            file = new BufferedReader(new FileReader(fileName));

            while((line = file.readLine()) != null) {

                if(line.matches(this.target)) {
                    found = true;
                    this.result.add(lineNumber, line);
                    ResultCounter.increment();
                }
                lineNumber++;
            }

            if(found) {
                System.out.println(String.join("\n", this.result.toString()));
            }
        } catch(FileNotFoundException e) {
            System.out.println(fileName + " NOT FOUND");
        } finally {

            if(file != null) {
                file.close();
            }
        }
    }

    private synchronized void load(){

        try{
            while(!Baton.isComplete()) {

                // wait until files are ready to collect
                while(!Baton.canCollect());

                // start looping through the files to look for the pattern
                for(String fileName: Baton.getFileNames()) {
                    this.find(fileName);
                }
                Baton.readyToLoad();
                // notify the loading thread waiting
            }
        } catch(IOException e) {
            System.out.println("Cannot find a data stream");
        } catch(Exception e) {
            //Restoring Thread Interruption Status
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        try {
            this.load();
        } catch(Exception e) {
            System.out.println("Something went wrong");
        }
    }
}