package com.marker55.apps.kui.domain.model;

import com.marker55.apps.kui.util.ObjectUtils;

/**
 * A model bean representing an application message.
 * 
 * @author Cameron Stokes
 */
public class ApplicationMessage {

    /**
     * The enum Outcome.
     * 
     * @author Cameron Stokes
     */
    public enum Outcome {

        /** Value representing a success. */
        SUCCESS,

        /** Value representing an error. */
        ERROR;

    }

    private String description;
    private String origin;

    private Object value;

    private Outcome outcome;

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the origin.
     * 
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Gets the outcome.
     * 
     * @return the outcome
     */
    public Outcome getOutcome() {
        return outcome;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public Object getValue() {
        return value;
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
     * Sets the origin.
     * 
     * @param origin the new origin
     */
    public void setOrigin( final String origin ) {
        this.origin = origin;
    }

    /**
     * Sets the outcome.
     * 
     * @param outcome the new outcome
     */
    public void setOutcome( final Outcome outcome ) {
        this.outcome = outcome;
    }

    /**
     * Sets the value.
     * 
     * @param value the new value
     */
    public void setValue( final Object value ) {
        this.value = value;
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
