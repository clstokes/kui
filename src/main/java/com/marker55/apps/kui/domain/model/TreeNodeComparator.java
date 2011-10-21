package com.marker55.apps.kui.domain.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator} implementation for ordering {@link TreeNode} objects.
 * 
 * @author Cameron Stokes
 */
public class TreeNodeComparator implements Comparator<TreeNode>, Serializable {

    private static final long serialVersionUID = -5343443147214692098L;

    /**
     * Compares the two {@link TreeNode} objects comparing their
     * {@link TreeNode#getText()} values.
     * 
     * @param o1 first object
     * @param o2 first object
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare( final TreeNode o1, final TreeNode o2 ) {
        return o1.getText().compareTo( o2.getText() );
    }

}
