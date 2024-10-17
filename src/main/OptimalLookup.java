package src.main;

import java.lang.Thread;

import src.argUtils.Options;

public class OptimalLookup{

    public static synchronized int find(String dir, String pattern, int level, int limit, Options options) {

        try {
            LoadFiles loadFiles = new LoadFiles(dir, level, limit);
            Lookup lookup = new Lookup(pattern, options.hasColor(), options.isShouldLogFileName());
            Thread loadFilesThread = new Thread(loadFiles), lookupThread = new Thread(lookup);
            lookupThread.start();
            loadFilesThread.start();
            lookupThread.join();
            loadFilesThread.join();
        } catch(Exception e) {
            System.out.println("Task took too long to complete.");
        }
        return Baton.getTotalSize();
    }
}