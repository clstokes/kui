package com.marker55.apps.kui.util;

import java.util.UUID;

/**
 * <p>
 * Utility methods for working with UUIDs.
 * </p>
 * 
 * @author Cameron Stokes
 */
public final class UUIDUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private UUIDUtils() {

    }

    /**
     * <p>
     * Returns a UUID String from the the JDK's {@link UUID} implementation.
     * </p>
     * 
     * @return UUID String
     */
    public static String getUUIDasString() {
        return UUID.randomUUID().toString();
    }

}
