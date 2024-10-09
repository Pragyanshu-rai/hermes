package src;

import src.main.OptimalLookup;

import src.results.ResultCounter;

import src.exceptions.NoArgException;
import src.exceptions.HelpArgException;

import src.argUtils.Arguments;
import src.argUtils.ArgumentsTransform;

public class App {

    private static Arguments arguments;

    private static ArgumentsTransform argumentsTransform;

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        argumentsTransform = new ArgumentsTransform(args);
        String source = null, target = null, option = null;

        try {
            arguments = argumentsTransform.getArguments();
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
        int totalCount = OptimalLookup.find(arguments.getSrc(), arguments.getPattern(), arguments.getLevel());
        final long endTime = System.currentTimeMillis();
        System.out.println("\nDONE\n\n"+ResultCounter.show()+" results found in "+ (endTime-startTime) + " ms from " + totalCount + " files.\n");
    }
}
