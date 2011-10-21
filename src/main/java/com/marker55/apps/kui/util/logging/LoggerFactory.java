package com.marker55.apps.kui.util.logging;

import com.marker55.apps.kui.util.logging.slf4j.SLF4JLogger;

/**
 * A factory for creating Logger objects.
 */
public class LoggerFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private LoggerFactory() {

    }

    /**
     * Return a logger named corresponding to the class passed as parameter.
     * 
     * @param clazz the clazz
     * @return the logger
     */
    public static final Logger getLogger( final Class<?> clazz ) {
        return new SLF4JLogger( clazz );
    }

}
