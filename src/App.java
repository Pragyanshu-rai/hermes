package src;

import src.argUtils.*;
import src.exceptions.*;
import src.main.OptimalLookup;
import src.results.ResultCounter;

public class App {

    private static ArgReader argReader;

    private static String name;

    private static String description;

    private static String usage;

    private static String example;

    private static String url;

    private static String try_prompt;

    private static TransformTarget transformTarget;

    static {
        name = "look";
        description = "Search for Patterns in Files.";
        usage = "look [OPTION]... PATTERNS [FILE]...";
        example = "look -w ./test/ hello";
        url = "http://localhost:3000";
        try_prompt = "Try 'look -h' for more information.";
        transformTarget = new TransformTarget();
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        String source = null, target = null, option = null;
        ParseArg sourceAndTarget = null;

        try {
            argReader = new ArgReader(args);
            sourceAndTarget = argReader.parse();
            option = sourceAndTarget.getOptions();
            source = sourceAndTarget.getSource();
            target = sourceAndTarget.getTarget();
        } catch (NoArgException nae) {
            System.out.println(nae);
            System.exit(1);
        } catch (HelpArgException hae) {
            System.out.println(hae);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Oops that was an unexpected Exception.");
            System.exit(1);
        }
        target = transformTarget.transform(option, target);
        int totalCount = OptimalLookup.find(source, target);
        final long endTime = System.currentTimeMillis();
        System.out.println("\nDONE\n\n"+ResultCounter.show()+" results found in "+ (endTime-startTime) + " ms from " + totalCount + " files.\n");
    }
}
