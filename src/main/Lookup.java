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

    private Vector<String> fileNames;

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
            System.out.println(filename + " NOT FOUND");
        } finally {

            if(file != null) {
                file.close();
            }
        }
    }

    private synchronized void load(){

        try{
            while(this.lookupComplete == false) {

                while(Baton.canCollect() == false) {
                    wait();
                }
                // load all the file names
                this.fileNames = Baton.getFileNames();

                // reset and resend the baton for loading fileNames
                Baton.readyToLoad();

                // start looping through the files to look for the pattern
                for(String fileName: this.fileNames) {
                    this.find(fileName);
                }

                // marks the completion of loading fileNames
                this.lookupComplete = Baton.isComplete();
            }
        } catch(InterruptedException e) {
            //Restoring Thread Interruption Status
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        try {
            this.load();
        } catch(IOException e) {
            System.out.println("Cannot find a data stream")
        } catch(Exception e) {
            System.out.println("Something went wrong");
        }
    }
}