package src.log;

public class Logger {

    public static void log(String logString) {
        System.out.print(logString);
    }
    public static void performanceLog(long resultCount, long time, int fileCount, boolean needsColor) {
        String logString =
                "\nDONE\n"
                + (needsColor ? Color.addBoldGreen(""+resultCount) : resultCount)
                + " results found in "
                + (needsColor ? Color.addBoldWhite(""+(time / 1000)) : (time / 1000))
                + " sec from "
                + (needsColor ? Color.addBoldWhite(""+fileCount) : fileCount)
                + " files.\n";

        log(logString);
    }
}