package com.marker55.apps.kui.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.marker55.apps.kui.util.ObjectUtils;

/**
 * A model bean representing an MBean operation.
 * 
 * @author Cameron Stokes
 */
public class MBeanOperation {

    private String name;
    private List<String> parameters;
    private String returnType;

    /**
     * Adds the parameter to the parameters {@link List}.
     * 
     * @param parameter the parameter
     */
    public void addParameter( final String parameter ) {
        if ( parameters == null ) {
            parameters = new ArrayList<String>();
        }
        parameters.add( parameter );
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
     * Gets the parameters.
     * 
     * @return the parameters
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Gets the return type.
     * 
     * @return the return type
     */
    public String getReturnType() {
        return returnType;
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
     * Sets the parameters.
     * 
     * @param parameters the new parameters
     */
    public void setParameters( final List<String> parameters ) {
        this.parameters = parameters;
    }

    /**
     * Sets the return type.
     * 
     * @param returnType the new return type
     */
    public void setReturnType( final String returnType ) {
        this.returnType = returnType;
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
