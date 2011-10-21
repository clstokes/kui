package com.marker55.apps.kui.controller;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.marker55.apps.kui.BaseException;
import com.marker55.apps.kui.domain.model.ApplicationMessage;
import com.marker55.apps.kui.util.ObjectUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * The super-class for any Spring controllers that need to return JSON values.
 * 
 * @author Cameron Stokes
 */
public abstract class JSONController implements Controller {

    private static final Logger logger = LoggerFactory.getLogger( JSONController.class );

    private static final String RESPONSE_HEADER_CONTENT_TYPE = "application/json";

    /**
     * Converts the given {@link Object} to JSON.
     * 
     * @param object the {@link Object} to convert
     * @return the JSON string
     * @throws ControllerException the controller exception
     */
    private String getJSONfromObject( final Object object ) throws ControllerException {

        if ( object == null ) {
            throw new ControllerException( "Cannot convert null object." );
        }

        try {
            String json = ObjectUtils.getObjectAsJSONString( object );
            logger.debug( "Converted [{}] to JSON.", object.getClass().getName() );
            return json;
        }
        catch ( final Throwable cause ) {
            throw new ControllerException( "ApplicationMessage while converting object to JSON.", cause );
        }
    }

    /**
     * Converts the given {@link ApplicationMessage} to JSON and returns in the
     * {@link HttpServletResponse}
     * 
     * @param applicationMessage the error
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws BaseException the base exception
     */
    protected ModelAndView returnError( final ApplicationMessage applicationMessage,
            final HttpServletResponse httpServletResponse ) throws BaseException {
        httpServletResponse.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        final String json = getJSONfromObject( applicationMessage );
        writeOutput( json, httpServletResponse );
        logger.debug( "Returned [{}] as response.", applicationMessage );
        return null;
    }

    /**
     * Converts the given {@link Object} to JSON and returns in the
     * {@link HttpServletResponse}
     * 
     * @param object the object
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws BaseException the base exception
     */
    protected ModelAndView returnJSON( final Object object, final HttpServletResponse httpServletResponse )
            throws BaseException {
        final String json = getJSONfromObject( object );
        writeOutput( json, httpServletResponse );
        return null;
    }

    /**
     * Writes the given output string to the {@link HttpServletResponse}.
     * 
     * @param output the output
     * @param httpServletResponse the {@link HttpServletResponse}
     * @throws ControllerException the controller exception
     */
    private void writeOutput( final String output, final HttpServletResponse httpServletResponse )
            throws ControllerException {
        try {

            httpServletResponse.setHeader( "Content-type", RESPONSE_HEADER_CONTENT_TYPE );

            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter( httpServletResponse.getOutputStream() );
            final BufferedWriter bufferedWriter = new BufferedWriter( outputStreamWriter );

            bufferedWriter.append( output );
            bufferedWriter.flush();

        }
        catch ( final Throwable cause ) {
            throw new ControllerException( "ApplicationMessage while writing response.", cause );
        }
    }

}
