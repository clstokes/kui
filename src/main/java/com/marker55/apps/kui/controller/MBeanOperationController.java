package com.marker55.apps.kui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.marker55.apps.kui.domain.model.ApplicationMessage;
import com.marker55.apps.kui.domain.model.ApplicationMessage.Outcome;
import com.marker55.apps.kui.domain.service.JMXService;
import com.marker55.apps.kui.util.ObjectUtils;
import com.marker55.apps.kui.util.RequestUtils;
import com.marker55.apps.kui.util.StringUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * The controller to invoke MBean operations based on the given request
 * parameters.
 * 
 * @author Cameron Stokes
 */
public class MBeanOperationController extends JSONController implements AuthenticatedFilter {

    private static final Logger logger = LoggerFactory.getLogger( MBeanOperationController.class );

    private static final String REQUEST_PARAM_MBEAN_NAME = "mbeanName";
    private static final String REQUEST_PARAM_OPERATION_NAME = "operationName";
    private static final String REQUEST_PARAM_PARAMETER_NAME = "parameter";

    private JMXService service;

    /**
     * Instantiates a new MBeanOperationController.
     * 
     * @param service the service
     */
    public MBeanOperationController( final JMXService service ) {
        this.service = service;
    }

    /**
     * Handles the Http request, checking for required request parameters and
     * invoking the MBean operation.
     * 
     * @param httpServletRequest the {@link HttpServletRequest}
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    public ModelAndView handleRequest( final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse ) throws Exception {

        final String mbeanId = RequestUtils.getRequestParameter( REQUEST_PARAM_MBEAN_NAME, httpServletRequest );
        final String operationName = RequestUtils
                .getRequestParameter( REQUEST_PARAM_OPERATION_NAME, httpServletRequest );
        final List<String> parameters = new ArrayList<String>();

        String currentParam = null;
        int i = 0;
        while ( ( currentParam = RequestUtils.getRequestParameter( createParameterName( i ), httpServletRequest ) ) != null ) {
            parameters.add( currentParam );
            i++;
        }

        if ( org.apache.commons.lang.StringUtils.isEmpty( mbeanId )
                || org.apache.commons.lang.StringUtils.isEmpty( operationName ) ) {
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Necessary request parameters not present." );
            return returnError( applicationMessage, httpServletResponse );
        }

        return handleInvokeOperation( mbeanId, operationName, parameters, httpServletResponse );

    }

    /**
     * Calls the {@link JMXService} to invoke the specified MBean operation.
     * 
     * @param mbeanName the mbean name
     * @param operationName the operation name
     * @param parameters the parameters
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    private ModelAndView handleInvokeOperation( final String mbeanName, final String operationName,
            final List<String> parameters, final HttpServletResponse httpServletResponse ) throws Exception {

        Object returnValue = null;

        try {
            String[] parameterArray = null;
            if ( !ObjectUtils.isEmpty( parameters ) ) {
                parameterArray = parameters.toArray( new String[ObjectUtils.sizeOf( parameters )] );
            }
            returnValue = getService().invokeOperation( mbeanName, operationName, parameterArray );
        }
        catch ( final Throwable cause ) {
            logger
                    .error( "Error while invoking operation [" + operationName + "] on MBean [" + mbeanName + "].",
                            cause );
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Unable to invoke operation." );
            return returnError( applicationMessage, httpServletResponse );
        }

        final ApplicationMessage applicationMessage = new ApplicationMessage();
        applicationMessage.setOutcome( Outcome.SUCCESS );
        applicationMessage.setDescription( "Operation invoked successfully." );
        applicationMessage.setValue( returnValue );

        logger.debug( "Invoked operation [{}] on MBean [{}].", operationName, mbeanName );

        return returnJSON( applicationMessage, httpServletResponse );
    }

    /**
     * Creates the request parameter name for the given number.
     * 
     * @param i number to use in the parameter name
     * @return the parameter name
     */
    private String createParameterName( final int i ) {
        return StringUtils.append( REQUEST_PARAM_PARAMETER_NAME, "[", String.valueOf( i ), "]" );
    }

    /**
     * Gets the service.
     * 
     * @return the {@link JMXService}
     */
    public JMXService getService() {
        return service;
    }

}
