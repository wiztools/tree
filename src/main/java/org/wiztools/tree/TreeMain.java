package org.wiztools.tree;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 *
 * @author subwiz
 */
public class TreeMain {
    private static void printHelp(PrintStream out) {
        out.println("Usage: java -jar tree-NN-jar-with-dependencies.jar [options] [path]");
        out.println("Where options are:");
        out.println("\t-h\tPrint this message.");
        out.println("\t-a\tAll files (including hidden directories and files) are printed.");
        out.println("\t-d\tList directories only.");
        out.println("\t-L level");
        out.println("\t  \tMax display depth of the directory tree.");
    }
    public static void main(String[] args) {
        OptionParser parser = new OptionParser("haL:d");
        
        try {
            OptionSet cliOpts = parser.parse(args);

            if(cliOpts.has("h")) {
                printHelp(System.out);
                return;
            }

            OptionsBean options = new OptionsBean();
            if(cliOpts.has("a")) {
                options.setShowHidden(true);
            }
            if(cliOpts.has("d")) {
                options.setDirOnly(true);
            }
            if(cliOpts.has("L")) {
                final String depthStr = (String) cliOpts.valueOf("L");
                try {
                    final int depth = Integer.parseInt(depthStr);
                    if(depth < 1) {
                        throw new IllegalArgumentException("");
                    }
                    options.setDepth(depth);
                }
                catch(NumberFormatException ex) {
                    throw new MyOptionException(
                            "-L option needs to be a value greater than 0",
                            ex);
                }
                catch(IllegalArgumentException ex) {
                    throw new MyOptionException(
                            "-L option needs to be a value greater than 0",
                            ex);
                }
            }

            List<String> argsList = cliOpts.nonOptionArguments();
            if(argsList.isEmpty()) {
                TreeTraverser tree = new TreeTraverser(new File("."), options);
            }
            else {
                for(final String arg: argsList) {
                    final File f = new File(arg);
                    if(f.exists() && f.canRead()) {
                        TreeTraverser tree = new TreeTraverser(f, options);
                    }
                    else {
                        System.err.println(
                                "File / folder does not exist / cannot read: " + arg);
                    }
                }
            }
        }
        catch(OptionException ex) {
            System.err.println(ex.getMessage());
            printHelp(System.err);
            System.exit(1);
        }
    }
    
    static class MyOptionException extends OptionException {
        public MyOptionException(String str,
                            Throwable t) {
            super(Arrays.asList(new String[]{str}), t);
        }
    }
}
