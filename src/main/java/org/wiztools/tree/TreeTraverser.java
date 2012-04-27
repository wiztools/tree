package org.wiztools.tree;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author subwiz
 */
public class TreeTraverser {
    private static final String PIPE_SYMBOL = "|   ";
    private static final String SPACE_SYMBOL = "    ";
    private static final String FOLDER_SYMBOL = "`-- ";
    private static final String FILE_SYMBOL = "|-- ";
    
    private final Set<Integer> pipeSymbolMarker = new HashSet<Integer>();
    
    private final FileFilter DIR_FILE_FILTER = new DirectoryFileFilter();
    
    private int depth = 0;
    
    private long fileCount;
    private long dirCount;
    
    private final OptionsBean options;
    
    private void print(String str) {
        System.out.print(str);
    }
    
    private void println(String str) {
        System.out.println(str);
    }
    
    private void printf(String str, Object ... o) {
        System.out.printf(str, o);
        System.out.println();
    }
    
    private void printCommon() {
        for(int i=0; i<depth-1; i++) {
            if(i==0 || pipeSymbolMarker.contains(i)) {
                print(PIPE_SYMBOL);
            }
            else {
                print(SPACE_SYMBOL);
            }
        }
    }
    
    private void printFolder(File file) {
        // Check if this method needs to be executed:
        if((!options.isShowHidden()) && file.isHidden()) {
            return;
        }
        if(!options.canTraverseDepth(depth + 1)) {
            return;
        }
        
        // init:
        depth++;
        dirCount++;
        
        // Print file-name logic:
        printCommon();
        print(FOLDER_SYMBOL);
        println(file.getName());
        
        // Pipe marker init:
        final File[] dirArr = file.listFiles(DIR_FILE_FILTER);
        final boolean pipeSymbolMarkerIsPresent = (dirArr != null) && (dirArr.length > 1);
        if(pipeSymbolMarkerIsPresent) {
            pipeSymbolMarker.add(depth);
        }
        
        // Recursion logic:
        final File[] children = file.listFiles();
        if(children != null) {
            for(File f: children) {
                if(f.isDirectory()) {
                    printFolder(f);
                }
                else {
                    printFile(f);
                }
            }
        }
        
        // Pipe marker clean:
        if(pipeSymbolMarkerIsPresent) {
            pipeSymbolMarker.remove(depth);
        }
        
        // clean:
        depth--;
    }
    
    private void printFile(File file) {
        // Check if this method needs to be executed:
        if(options.isDirOnly()) {
            return;
        }
        if((!options.isShowHidden()) && file.isHidden()) {
            return;
        }
        if(!options.canTraverseDepth(depth + 1)) {
            return;
        }
        
        // init:
        depth++;
        fileCount++;
        
        // Logic:
        printCommon();
        print(FILE_SYMBOL);
        println(file.getName());
        
        // clean:
        depth--;
    }
    
    public TreeTraverser(File file, OptionsBean options) {
        this.options = options;
        
        // Start by printing the supplied folder / file name:
        println(file.getName());
        
        if(file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for(File f: subFiles) {
                if(f.isDirectory()) {
                    printFolder(f);
                }
                else {
                    printFile(f);
                }
            }
        }
        else { // is standard file
            // do nothing!
        }
        if(options.isDirOnly()) {
            printf("%d directories", dirCount);
        }
        else {
            printf("%d directories, %d files", dirCount, fileCount);
        }
    }
    
    private class DirectoryFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory()) {
                if((!options.isShowHidden()) && file.isHidden()) {
                    return false;
                }
                return true;
            }
            return false;
        }
        
    }
}
