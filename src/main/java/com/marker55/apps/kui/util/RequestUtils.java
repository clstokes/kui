package com.marker55.apps.kui.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Utility methods for working with Servlet requests and responses.
 * 
 * @author Cameron Stokes
 */
public final class RequestUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private RequestUtils() {

    }

    /**
     * Gets the request parameter.
     * 
     * @param parameterName the parameter name
     * @param httpServletRequest the http servlet request
     * @return the request parameter
     */
    public static String getRequestParameter( final String parameterName, final HttpServletRequest httpServletRequest ) {
        return httpServletRequest.getParameter( parameterName );
    }

}
