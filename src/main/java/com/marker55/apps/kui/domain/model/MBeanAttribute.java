package com.marker55.apps.kui.domain.model;

import com.marker55.apps.kui.util.ObjectUtils;

/**
 * A model bean representing an MBean attribute.
 * 
 * @author Cameron Stokes
 */
public class MBeanAttribute {

    private String name;
    private String value;

    private String type;

    private boolean available;
    private boolean writable;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Checks if is available.
     * 
     * @return true, if is available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Checks if is writable.
     * 
     * @return true, if is writable
     */
    public boolean isWritable() {
        return writable;
    }

    /**
     * Sets the available.
     * 
     * @param available the new available
     */
    public void setAvailable( final boolean available ) {
        this.available = available;
    }

    /**
     * Sets the name.
     * 
     * @param name the new name
     */
    public void setName( final String name ) {
        this.name = name;
    }

    /**
     * Sets the type.
     * 
     * @param type the new type
     */
    public void setType( final String type ) {
        this.type = type;
    }

    /**
     * Sets the value.
     * 
     * @param value the new value
     */
    public void setValue( final String value ) {
        this.value = value;
    }

    /**
     * Sets the writable.
     * 
     * @param writable the new writable
     */
    public void setWritable( final boolean writable ) {
        this.writable = writable;
    }

    /**
     * Returns a String representation of this object.
     * 
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return ObjectUtils.toString( this );
    }

}
