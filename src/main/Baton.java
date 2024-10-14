package src.main;

import java.util.Vector;

public class Baton {

    private static int size;

    private static boolean isDone;

    private static boolean isLoaded;

    private static Vector<String> fileNames;

    static {
        size = 0;
        isDone = false;
        isLoaded = false;
        fileNames = new Vector<String>();
    }

    public static void add(String fileName) {
        Baton.size++;
        Baton.fileNames.add(fileName);
    }

    private static Vector<String> isCollected() {
        Baton.size = 0;
        Baton.fileNames.clear();
    }

    public static Vector<String> getFileNames() {
        return Baton.fileNames;
    }

    public static void readyToCollect() {
        Baton.isLoaded = true;
    }

    public static void readyToLoad() {
        Baton.isCollected();
        Baton.isLoaded = false;
    }

    public static Boolean canCollect() {
        return Baton.isLoaded;
    }

    public static int getSize() {
        return Baton.size;
    }

    public static void complete() {
        this.isDone = true;
    }

    public static boolean isComplete() {
        return this.isDone;
    }
}