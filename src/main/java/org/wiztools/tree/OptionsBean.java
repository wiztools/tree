package org.wiztools.tree;

/**
 *
 * @author subwiz
 */
public class OptionsBean implements Options {
    private boolean dirOnly = false;
    private int depth = -1;
    private boolean showHidden = false;

    @Override
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    @Override
    public boolean canTraverseDepth(final int myDepth) {
        if(depth != -1 && myDepth > depth) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isDirOnly() {
        return dirOnly;
    }

    public void setDirOnly(boolean dirOnly) {
        this.dirOnly = dirOnly;
    }

    @Override
    public boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(boolean showHidden) {
        this.showHidden = showHidden;
    }
}
