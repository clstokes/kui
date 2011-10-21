package com.marker55.apps.kui.util.logging;

/**
 * The Logger interface.
 * 
 * @author Cameron Stokes
 */
public interface Logger {

    /**
     * Log a message at the DEBUG level according to the specified format and
     * argument.
     * 
     * @param message the message
     * @param throwable the throwable
     */
    void debug( final String message, final Throwable throwable );

    /**
     * Log a message at the DEBUG level.
     * 
     * @param message the message
     * @param objects objects to include in the message
     */
    void debug( final String message, final Object... objects );

    /**
     * Log a message at the DEBUG level.
     * 
     * @param message the message
     */
    void debug( final String message );

    /**
     * Log a message at the INFO level according to the specified format and
     * argument.
     * 
     * @param message the message
     * @param throwable the throwable
     */
    void info( final String message, final Throwable throwable );

    /**
     * Log a message at the INFO level.
     * 
     * @param message the message
     * @param objects objects to include in the message
     */
    void info( final String message, final Object... objects );

    /**
     * Log a message at the INFO level.
     * 
     * @param message the message
     */
    void info( final String message );

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * 
     * @param message the message
     * @param throwable the throwable
     */
    void error( final String message, final Throwable throwable );

    /**
     * Log a message at the ERROR level.
     * 
     * @param message the message
     * @param objects objects to include in the message
     */
    void error( final String message, final Object... objects );

    /**
     * Log a message at the ERROR level.
     * 
     * @param message the message
     */
    void error( final String message );

}
