package org.wiztools.tree;

/**
 *
 * @author subwiz
 */
public class OptionsBean {
    private boolean dirOnly = false;
    private int depth = -1;
    private boolean showHidden = false;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public boolean canTraverseDepth(final int myDepth) {
        if(depth != -1 && myDepth > depth) {
            return false;
        }
        return true;
    }

    public boolean isDirOnly() {
        return dirOnly;
    }

    public void setDirOnly(boolean dirOnly) {
        this.dirOnly = dirOnly;
    }

    public boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(boolean showHidden) {
        this.showHidden = showHidden;
    }
}
