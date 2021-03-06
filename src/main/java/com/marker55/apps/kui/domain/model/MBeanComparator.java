package com.marker55.apps.kui.domain.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator} implementation for ordering {@link MBean} objects.
 * 
 * @author Cameron Stokes
 */
public class MBeanComparator implements Comparator<MBean>, Serializable {

    private static final long serialVersionUID = 2344999422596158232L;

    /**
     * Compares the two {@link MBean} objects comparing their
     * {@link MBean#getName()} values.
     * 
     * @param o1 first object
     * @param o2 first object
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare( final MBean o1, final MBean o2 ) {
        return o1.getName().compareTo( o2.getName() );
    }

}
