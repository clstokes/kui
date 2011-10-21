package com.marker55.apps.kui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.marker55.apps.kui.domain.model.ApplicationMessage;
import com.marker55.apps.kui.domain.model.ApplicationMessage.Outcome;
import com.marker55.apps.kui.domain.service.JMXService;
import com.marker55.apps.kui.util.RequestUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * The controller to set MBean attributes based on the given request parameters.
 * 
 * @author Cameron Stokes
 */
public class MBeanAttributeController extends JSONController implements AuthenticatedFilter {

    private static final Logger logger = LoggerFactory.getLogger( MBeanAttributeController.class );

    private static final String REQUEST_PARAM_MBEAN_NAME = "mbeanName";
    private static final String REQUEST_PARAM_ATTRIBUTE_NAME = "attributeName";
    private static final String REQUEST_PARAM_ATTRIBUTE_VALUE = "attributeValue";

    private JMXService service;

    /**
     * Instantiates a new MBeanAttributeController.
     * 
     * @param service the service
     */
    public MBeanAttributeController( final JMXService service ) {
        this.service = service;
    }

    /**
     * Handles the Http request, checking for required request parameters and
     * setting the MBean attribute.
     * 
     * @param httpServletRequest the http servlet request
     * @param httpServletResponse the http servlet response
     * @return the model and view
     * @throws Exception the exception
     */
    public ModelAndView handleRequest( final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse ) throws Exception {

        final String mbeanId = RequestUtils.getRequestParameter( REQUEST_PARAM_MBEAN_NAME, httpServletRequest );
        final String attributeName = RequestUtils
                .getRequestParameter( REQUEST_PARAM_ATTRIBUTE_NAME, httpServletRequest );
        final String attributeValue = RequestUtils.getRequestParameter( REQUEST_PARAM_ATTRIBUTE_VALUE,
                httpServletRequest );

        if ( StringUtils.isEmpty( mbeanId ) || StringUtils.isEmpty( attributeName )
                || StringUtils.isEmpty( attributeValue ) ) {
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Necessary request parameters not present." );
            return returnError( applicationMessage, httpServletResponse );
        }

        return handleSetAttributeRequest( mbeanId, attributeName, attributeValue, httpServletResponse );

    }

    /**
     * Handles a request to set an MBean attribute.
     * 
     * @param mbeanName the mbean name
     * @param attributeName the attribute name
     * @param attributeValue the attribute value
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    private ModelAndView handleSetAttributeRequest( final String mbeanName, final String attributeName,
            final String attributeValue, final HttpServletResponse httpServletResponse ) throws Exception {

        try {
            getService().setMBeanAttribute( mbeanName, attributeName, attributeValue );
        }
        catch ( final Exception cause ) {
            logger.error( "Error while setting attribute [" + attributeName + "] to value [" + attributeValue
                    + "] on mbean [" + mbeanName + "]", cause );
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Unable to set attribute value." );
            return returnError( applicationMessage, httpServletResponse );
        }

        final ApplicationMessage applicationMessage = new ApplicationMessage();
        applicationMessage.setOutcome( Outcome.SUCCESS );
        applicationMessage.setDescription( "Attribute set successfully." );

        logger.debug( "Set attribute [{}] to value [{}] on MBean [{}].", attributeName, attributeValue, mbeanName );

        return returnJSON( applicationMessage, httpServletResponse );
    }

    /**
     * Gets the service.
     * 
     * @return the service
     */
    public JMXService getService() {
        return service;
    }

}
