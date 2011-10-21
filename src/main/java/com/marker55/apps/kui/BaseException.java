package com.marker55.apps.kui;

import com.marker55.apps.kui.util.UUIDUtils;

/**
 * Super-class for all application-specific exceptions.
 * 
 * @author Cameron Stokes
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 7269737393822650833L;

    private final String id = UUIDUtils.getUUIDasString();

    /**
     * Instantiates a new base exception.
     * 
     * @param message the message
     */
    public BaseException( final String message ) {
        super( message );
    }

    /**
     * The Constructor.
     * 
     * @param message the message
     * @param cause the cause
     */
    public BaseException( final String message, final Throwable cause ) {
        super( message, cause );
    }

    /**
     * Returns the ID for this exception instance.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

}
