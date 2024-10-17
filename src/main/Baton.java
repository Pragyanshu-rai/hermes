package src.main;

import java.util.Vector;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Baton {

    private static int size;

    private static Lock lock;

    private static int totalSize;

    private static boolean isDone;

    private static boolean isLoaded;

    private static Vector<String> fileNames;

    static {
        size = 0;
        totalSize = 0;
        isDone = false;
        isLoaded = false;
        lock = new ReentrantLock();
        fileNames = new Vector<String>();
    }

    public static synchronized void add(String fileName) {
        lock.lock();

        try{
            Baton.size++;
            Baton.fileNames.add(fileName);
        } finally {
            lock.unlock();
        }
    }

    private static synchronized void isCollected() {
        Baton.totalSize += Baton.size;
        Baton.size = 0;
        Baton.fileNames = new Vector<String>();
    }

    public static synchronized void readyToCollect() {
        lock.lock();

        try{
            Baton.isLoaded = true;
        } finally {
            lock.unlock();
        }
    }

    public static synchronized void readyToLoad() {
        lock.lock();

        try{
            Baton.isCollected();
            Baton.isLoaded = false;
        } finally {
            lock.unlock();
        }
    }

    public static synchronized Boolean canCollect() {
        return Baton.isLoaded;
    }

    public static synchronized int getSize() {
        return Baton.size;
    }

    public static synchronized void complete() {
        lock.lock();

        try{
            Baton.readyToCollect();
            Baton.isDone = true;
        } finally {
            lock.unlock();
        }
    }

    public static synchronized boolean isComplete() {
        return Baton.isDone;
    }

    public static synchronized int getTotalSize() {
        return Baton.totalSize;
    }

    public static synchronized Vector<String> getFileNames() {
        return Baton.fileNames;
    }
}