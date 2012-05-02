package org.wiztools.tree;

/**
 *
 * @author subwiz
 */
public class CounterImpl implements Counter {
    
    private long folderCount = 0;
    private long fileCount = 0;

    @Override
    public void incrementFolderCount() {
        folderCount++;
    }

    @Override
    public void incrementFileCount() {
        fileCount++;
    }

    @Override
    public long getFolderCount() {
        return folderCount;
    }

    @Override
    public long getFileCount() {
        return fileCount;
    }
    
}
