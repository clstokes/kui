package com.marker55.apps.kui.util.logging.slf4j;

import com.marker55.apps.kui.util.logging.Logger;

/**
 * SLF4J implementation of the Logger interface.
 * 
 * @author Cameron Stokes
 */
public class SLF4JLogger implements Logger {

    /** The logger. */
    private org.slf4j.Logger logger;

    /**
     * Instantiates a new sL f4 j logger.
     * 
     * @param clazz the clazz
     */
    public SLF4JLogger( final Class<?> clazz ) {
        logger = org.slf4j.LoggerFactory.getLogger( clazz );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#debug(java.lang.String,
     * java.lang.Throwable)
     */
    public void debug( String message, Throwable throwable ) {
        logger.debug( message, throwable );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#debug(java.lang.String,
     * java.lang.Object[])
     */
    public void debug( String message, final Object... objects ) {
        logger.debug( message, objects );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#debug(java.lang.String)
     */
    public void debug( String message ) {
        logger.debug( message );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#info(java.lang.String,
     * java.lang.Throwable)
     */
    public void info( String message, Throwable throwable ) {
        logger.info( message, throwable );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#info(java.lang.String,
     * java.lang.Object[])
     */
    public void info( String message, final Object... objects ) {
        logger.debug( message, objects );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#info(java.lang.String)
     */
    public void info( String message ) {
        logger.info( message );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#error(java.lang.String,
     * java.lang.Throwable)
     */
    public void error( String message, Throwable throwable ) {
        logger.error( message, throwable );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#error(java.lang.String,
     * java.lang.Object[])
     */
    public void error( String message, final Object... objects ) {
        logger.debug( message, objects );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.marker55.apps.kui.util.logging.Logger#error(java.lang.String)
     */
    public void error( String message ) {
        logger.error( message );
    }

}
