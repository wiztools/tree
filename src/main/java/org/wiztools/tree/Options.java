/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiztools.tree;

/**
 *
 * @author subwiz
 */
public interface Options {

    boolean canTraverseDepth(final int myDepth);

    int getDepth();

    boolean isDirOnly();

    boolean isShowHidden();
    
}
