package src.main;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;

public class OptimalLookup {

    public static final int LIMIT = 200;

    private static int count = 0;

    public static int find(String dir, String target, int level) {

        try {
            ThreadPoolExecutor pool = new ThreadPoolExecutor(200, 250, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(600), new ThreadPoolExecutor.CallerRunsPolicy());
            GetFiles getFiles = new GetFiles(dir, level);
            Vector<Vector<String>> names_list = getFiles.fetchBatch(200);

            for(Vector<String> names : names_list) {

                for (String name: names) {
                    pool.execute(new Finder(name, target));
                    count++;
                }

                while (pool.getTaskCount() != pool.getCompletedTaskCount()) {
                    System.out.print("SCANNING FILES.... \r");
                };
            }
            pool.shutdown();
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            System.out.println("Tasks took too long to complete.");
        }
        return count;
    }
}
