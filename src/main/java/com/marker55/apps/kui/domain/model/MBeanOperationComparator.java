package com.marker55.apps.kui.domain.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator} implementation for ordering {@link MBeanOperation}
 * objects.
 * 
 * @author Cameron Stokes
 */
public class MBeanOperationComparator implements Comparator<MBeanOperation>, Serializable {

    private static final long serialVersionUID = -8682894074894501297L;

    /**
     * Compares the two {@link MBeanOperation} objects comparing their
     * {@link MBeanOperation#getName()} values.
     * 
     * @param o1 first object
     * @param o2 first object
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare( final MBeanOperation o1, final MBeanOperation o2 ) {
        return o1.getName().compareTo( o2.getName() );
    }

}
