package com.marker55.apps.kui.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.marker55.apps.kui.controller.AuthenticatedFilter;
import com.marker55.apps.kui.controller.ConfigurationController;
import com.marker55.apps.kui.domain.model.UserSession;
import com.marker55.apps.kui.domain.service.ConfigurationService;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger( AuthenticationInterceptor.class );

    private ConfigurationService configurationService;

    public AuthenticationInterceptor( ConfigurationService configurationService ) {
        this.configurationService = configurationService;
    }

    public boolean preHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object handler ) throws Exception {

        if ( !getConfigurationService().getConfiguration().isAuthenticationRequired() ) {
            logger.debug( "Authentication not required." );
            return true;
        }

        if ( !( handler instanceof AuthenticatedFilter ) ) {
            logger.debug( "Not authenticating controller [{}].", handler.getClass().getName() );
            return true;
        }

        logger.debug( "Authenticating controller [{}].", handler.getClass().getName() );

        boolean validSession = false;
        final HttpSession httpSession = httpServletRequest.getSession( false );
        if ( httpSession == null ) {
            validSession = false;
        }
        else {
            final UserSession userSession = (UserSession) httpSession.getAttribute( UserSession.class.getName() );
            validSession = userSession.isAuthenticated();
        }

        if ( !validSession ) {
            logger.debug( "Returning [{}] response.", HttpServletResponse.SC_FORBIDDEN );
            httpServletResponse.setStatus( HttpServletResponse.SC_FORBIDDEN );
            return false;
        }

        return true;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

}
