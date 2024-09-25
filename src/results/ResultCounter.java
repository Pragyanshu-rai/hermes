package src.results;

public class ResultCounter {

    private static long counter;

    static {
        counter = 0;
    }

    public static synchronized void increment() {
        counter++;
    }

    public static synchronized long show() {
        return counter;
    }
}