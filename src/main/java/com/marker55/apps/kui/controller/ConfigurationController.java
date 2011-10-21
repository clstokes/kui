package com.marker55.apps.kui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.marker55.apps.kui.domain.model.ApplicationMessage;
import com.marker55.apps.kui.domain.model.Configuration;
import com.marker55.apps.kui.domain.model.ApplicationMessage.Outcome;
import com.marker55.apps.kui.domain.service.ConfigurationService;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

public class ConfigurationController extends JSONController {

    private static final Logger logger = LoggerFactory.getLogger( ConfigurationController.class );

    private ConfigurationService configurationService;

    public ConfigurationController( ConfigurationService configurationService ) {
        this.configurationService = configurationService;
    }

    public ModelAndView handleRequest( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse )
            throws Exception {

        Configuration configuration = null;
        try {
            configuration = getConfigurationService().getConfiguration();
        }
        catch ( Exception cause ) {
            logger.error( "Error while getting configuration.", cause );
            final ApplicationMessage applicationMessage = new ApplicationMessage();
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Unable to get Configuration." );
            return returnError( applicationMessage, httpServletResponse );
        }

        final ApplicationMessage applicationMessage = new ApplicationMessage();
        applicationMessage.setValue( configuration );
        if ( configuration == null ) {
            applicationMessage.setOutcome( Outcome.ERROR );
            applicationMessage.setDescription( "Unable to get Configuration." );
            logger.debug( "No configuration to return." );
        }
        else {
            applicationMessage.setOutcome( Outcome.SUCCESS );
            applicationMessage.setDescription( "Returning configuration." );
            logger.debug( "Returning configuration [{}].", configuration );
        }

        return returnJSON( applicationMessage, httpServletResponse );
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

}
