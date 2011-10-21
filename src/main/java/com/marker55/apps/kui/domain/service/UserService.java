package com.marker55.apps.kui.domain.service;

import com.marker55.apps.kui.domain.model.UserSession;
import com.marker55.apps.kui.util.StringUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger( UserService.class );
    
    private final String systemPropertyUsername;
    private final String systemPropertyPassword;
    
    public UserService( final String username, final String password ) {
        systemPropertyUsername = username;
        systemPropertyPassword = password;
    }

    public UserSession getUser( final String username, final String password ) {

        if ( StringUtils.equals( systemPropertyUsername, username ) && StringUtils.equals( systemPropertyPassword, password ) ) {
            logger.debug( "Authenticated user [{}].", username );
            final UserSession user = new UserSession();
            user.setUsername( username );
            return user;
        }

        logger.debug( "Invalid user [{}].", username );

        return null;
    }

    public boolean isAuthenticationRequired() {
        return !StringUtils.isEmpty( systemPropertyUsername ) || !StringUtils.isEmpty( systemPropertyPassword );
    }

}
