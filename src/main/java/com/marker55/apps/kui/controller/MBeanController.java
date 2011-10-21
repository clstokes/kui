package com.marker55.apps.kui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.marker55.apps.kui.domain.model.MBean;
import com.marker55.apps.kui.domain.model.TreeNode;
import com.marker55.apps.kui.domain.service.JMXService;
import com.marker55.apps.kui.util.ObjectUtils;
import com.marker55.apps.kui.util.RequestUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * The controller to provide MBean information based on the given request
 * parameters.
 * 
 * @author Cameron Stokes
 */
public class MBeanController extends JSONController implements AuthenticatedFilter {

    private static final Logger logger = LoggerFactory.getLogger( MBeanController.class );

    private static final String REQUEST_PARAM_ID = "id";

    private JMXService service;

    /**
     * Instantiates a new MBeanController.
     * 
     * @param service the service
     */
    public MBeanController( final JMXService service ) {
        this.service = service;
    }

    /**
     * Handles the Http request, checking for required request parameters and
     * returning the MBean or MBean list.
     * 
     * @param httpServletRequest the {@link HttpServletRequest}
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    public ModelAndView handleRequest( final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse ) throws Exception {

        final String mbeanId = RequestUtils.getRequestParameter( REQUEST_PARAM_ID, httpServletRequest );
        if ( StringUtils.isNotEmpty( mbeanId ) ) {
            return handleMBeanRequest( mbeanId, httpServletResponse );
        }
        return handleMBeanListRequest( httpServletResponse );

    }

    /**
     * Handles a request for the list of available MBeans.
     * 
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    private ModelAndView handleMBeanListRequest( final HttpServletResponse httpServletResponse ) throws Exception {
        final TreeNode[] mbeanList = getService().getMBeanArray();

        logger.debug( "Returning TreeNode array of size [{}].", ObjectUtils.sizeOf( mbeanList ) );

        return returnJSON( mbeanList, httpServletResponse );
    }

    /**
     * Handles a request for a specific MBean.
     * 
     * @param mbeanName the mbean name
     * @param httpServletResponse the {@link HttpServletResponse}
     * @return the {@link ModelAndView}
     * @throws Exception the exception
     */
    private ModelAndView handleMBeanRequest( final String mbeanName, final HttpServletResponse httpServletResponse )
            throws Exception {

        final MBean mbean = getService().getMBean( mbeanName );

        logger.debug( "Returning MBean [{}].", mbeanName );

        return returnJSON( mbean, httpServletResponse );
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
