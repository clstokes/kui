package com.marker55.apps.kui.controller;

import com.marker55.apps.kui.BaseException;

/**
 * The {@link BaseException} sub-class specific to controller functions.
 * 
 * @author Cameron Stokes
 */
public class ControllerException extends BaseException {

    private static final long serialVersionUID = -9080462883702023994L;

    /**
     * Instantiates a new ControllerException.
     * 
     * @param message the message
     */
    public ControllerException( final String message ) {
        super( message );
    }

    /**
     * Instantiates a new ControllerException.
     * 
     * @param message the message
     * @param cause the cause
     */
    public ControllerException( final String message, final Throwable cause ) {
        super( message, cause );
    }

}
