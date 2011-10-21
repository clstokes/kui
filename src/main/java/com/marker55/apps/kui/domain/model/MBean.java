package com.marker55.apps.kui.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.marker55.apps.kui.util.ObjectUtils;

/**
 * A model bean representing an MBean.
 * 
 * @author Cameron Stokes
 */
public class MBean {

    private String name;
    private String displayName;
    private String className;

    private String description;

    private List<MBeanAttribute> attributes;
    private List<MBeanOperation> operations;

    /**
     * Adds the attribute.
     * 
     * @param attribute the attribute
     */
    public void addAttribute( final MBeanAttribute attribute ) {
        if ( attributes == null ) {
            attributes = new ArrayList<MBeanAttribute>();
        }
        attributes.add( attribute );
    }

    /**
     * Adds the operation.
     * 
     * @param operation the operation
     */
    public void addOperation( final MBeanOperation operation ) {
        if ( operations == null ) {
            operations = new ArrayList<MBeanOperation>();
        }
        operations.add( operation );
    }

    /**
     * Gets the attributes.
     * 
     * @return the attributes
     */
    public List<MBeanAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Gets the class name.
     * 
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the display name.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the operations.
     * 
     * @return the operations
     */
    public List<MBeanOperation> getOperations() {
        return operations;
    }

    /**
     * Sets the attributes.
     * 
     * @param attributes the new attributes
     */
    public void setAttributes( final List<MBeanAttribute> attributes ) {
        this.attributes = attributes;
    }

    /**
     * Sets the class name.
     * 
     * @param className the new class name
     */
    public void setClassName( final String className ) {
        this.className = className;
    }

    /**
     * Sets the description.
     * 
     * @param description the new description
     */
    public void setDescription( final String description ) {
        this.description = description;
    }

    /**
     * Sets the display name.
     * 
     * @param displayName the new display name
     */
    public void setDisplayName( final String displayName ) {
        this.displayName = displayName;
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
     * Sets the operations.
     * 
     * @param operations the new operations
     */
    public void setOperations( final List<MBeanOperation> operations ) {
        this.operations = operations;
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
