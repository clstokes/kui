package com.marker55.apps.kui.domain.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator} implementation for ordering {@link MBeanAttribute}
 * objects.
 * 
 * @author Cameron Stokes
 */
public class MBeanAttributeComparator implements Comparator<MBeanAttribute>, Serializable {

    private static final long serialVersionUID = 7656430306378079744L;

    /**
     * Compares the two {@link MBeanAttribute} objects comparing their
     * {@link MBeanAttribute#getName()} values.
     * 
     * @param o1 first object
     * @param o2 first object
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare( final MBeanAttribute o1, final MBeanAttribute o2 ) {
        return o1.getName().compareTo( o2.getName() );
    }

}
