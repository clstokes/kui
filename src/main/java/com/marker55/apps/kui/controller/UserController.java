package com.marker55.apps.kui.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.marker55.apps.kui.domain.model.ApplicationMessage;
import com.marker55.apps.kui.domain.model.UserSession;
import com.marker55.apps.kui.domain.model.ApplicationMessage.Outcome;
import com.marker55.apps.kui.domain.service.UserService;
import com.marker55.apps.kui.util.RequestUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

public class UserController extends JSONController {

    private static final Logger logger = LoggerFactory.getLogger( UserController.class );

    private static final String REQUEST_PARAM_USERNAME = "username";
    private static final String REQUEST_PARAM_PASSWORD = "password";

    private UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    public ModelAndView handleRequest( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
            throws Exception {

        final String username = RequestUtils.getRequestParameter( REQUEST_PARAM_USERNAME, httpServletRequest );
        final String password = RequestUtils.getRequestParameter( REQUEST_PARAM_PASSWORD, httpServletRequest );

        UserSession user = null;
        try {
            user = getUserService().getUser( username, password );
        }
        catch ( Exception cause ) {
            logger.error( "Error while authenticating [" + username + "].", cause );
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Unable to authenticate user." );
            return returnError( applicationMessage, httpServletResponse );
        }

        final ApplicationMessage applicationMessage = new ApplicationMessage();
        applicationMessage.setValue( user );
        if ( user == null ) {
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Invalid username or password." );
            logger.debug( "Invalid user [{}].", username );
        }
        else {
            applicationMessage.setOutcome( Outcome.SUCCESS );
            applicationMessage.setDescription( "User authenticated." );

            user.setAuthenticated( true );
            user.setAuthenticatedTime( Calendar.getInstance().getTime() );
            httpServletRequest.getSession( true ).setAttribute( UserSession.class.getName(), user );

            logger.debug( "User [{}] authenticated.", username );

        }

        return returnJSON( applicationMessage, httpServletResponse );
    }

    public UserService getUserService() {
        return userService;
    }

}
