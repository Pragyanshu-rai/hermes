package src.main;

import java.io.File;
import java.io.IOException;

import java.lang.Thread;
import java.lang.Runnable;
import java.lang.InterruptedException;

class LoadFiles implements Runnable{

    private String dir;

    private int level;

    private int limit;

    private File curDir;

    public LoadFiles(String dir, int level, int limit) {
        this.dir = dir;
        this.level = level;
        this.limit = limit;
        this.curDir - new File(dir);
    }

    private synchronized void populateAll(File[] files, int level) {

        if (files == null || files.length == 0 || level == 0) {
            return;
        }
        Vector<String> dirList = new Vector<String>();

        try{

            for(File file : files) {

                try{
                    if (file.isFile()) {
                        Baton.add(file.getCanonicalPath());
                    } else if (file.isDirectory()) {
                        dirList.add(file.getCanonicalPath());
                    }
               } catch (IOException e) {
                    System.out.println("Unable to extract file name.");
                }
            }

            // if size limit is reached or exceeded then stop loading and notify
            if(Baton.getSize() >= this.limit) {
                Baton.readyToLoad();
                notify();
            }

            // if loading is stopped and is ready for collection and wait till collected
            while(Baton.canCollect()) {
                wait();
            }

            for(String dirName : dirList) {
                File[] fileArr = new File(dirName).listFiles();
                populateAll(fileArr, level-1);
            }
        } catch (InterruptedException e) {
            // Restore Interrupted Status
            Thread.currentThread().interrupt();
        }
        return;
    }

    private synchronized void load() {
        this.populateAll(this.curDir.listFiles(), this.level);
        Baton.readyToLoad();
        Baton.complete();
        notify();
    }

    public void run() {
        this.load();
    }
}