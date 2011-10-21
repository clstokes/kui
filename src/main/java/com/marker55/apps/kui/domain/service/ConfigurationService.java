package com.marker55.apps.kui.domain.service;

import com.marker55.apps.kui.domain.model.Configuration;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

public class ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger( ConfigurationService.class );

    private UserService userService;

    public ConfigurationService( UserService userService ) {
        this.userService = userService;
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setAuthenticationRequired( getUserService().isAuthenticationRequired() );

        logger.debug( "Returning Configuration [{}]", configuration );

        return configuration;
    }

    public UserService getUserService() {
        return userService;
    }

}
