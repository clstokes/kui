package com.marker55.apps.kui.util;

/**
 * Utility methods for working with Strings.
 * 
 * @author Cameron Stokes
 */
public final class StringUtils extends org.apache.commons.lang.StringUtils {

    /** The default length for a new String. */
    public static final int DEFAULT_STRING_LENGTH = 128;

    /**
     * Private constructor to prevent instantiation.
     */
    private StringUtils() {

    }

    /**
     * <p>
     * Appends the given strings together using a {@link StringBuilder}.
     * </p>
     * 
     * @param strings string values to append to each other
     * @return String
     */
    public static String append( final String... strings ) {

        if ( ( strings == null ) || ( strings.length == 0 ) ) {
            return null;
        }

        final StringBuilder stringBuilder = new StringBuilder( DEFAULT_STRING_LENGTH );

        for ( final String string : strings ) {
            stringBuilder.append( string );
        }

        return stringBuilder.toString();
    }

    /**
     * <p>
     * Appends the given strings to the given {@link StringBuilder}
     * </p>
     * 
     * @param originalBuilder {@link StringBuilder} to add the strings to
     * @param strings string values to add to the given {@link StringBuilder}
     * @return String
     */
    public static String append( final StringBuilder originalBuilder, final String... strings ) {

        if ( ( strings == null ) || ( strings.length == 0 ) ) {
            return null;
        }

        StringBuilder stringBuilder = originalBuilder;

        if ( stringBuilder == null ) {
            stringBuilder = new StringBuilder( DEFAULT_STRING_LENGTH );
        }

        for ( final String string : strings ) {
            stringBuilder.append( string );
        }

        return stringBuilder.toString();
    }

}
