package org.wiztools.tree;

/**
 *
 * @author subwiz
 */
public interface Counter {
    void incrementFolderCount();
    void incrementFileCount();
    
    long getFolderCount();
    long getFileCount();
}
