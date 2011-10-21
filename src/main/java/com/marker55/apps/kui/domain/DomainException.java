package com.marker55.apps.kui.domain;

import com.marker55.apps.kui.BaseException;

/**
 * The {@link BaseException} sub-class specific to domain functions.
 * 
 * @author Cameron Stokes
 */
public class DomainException extends BaseException {

    private static final long serialVersionUID = -9080462883702023994L;

    /**
     * Instantiates a new DomainException.
     * 
     * @param message the message
     */
    public DomainException( final String message ) {
        super( message );
    }

    /**
     * Instantiates a new DomainException.
     * 
     * @param message the message
     * @param cause the cause
     */
    public DomainException( final String message, final Throwable cause ) {
        super( message, cause );
    }

}
