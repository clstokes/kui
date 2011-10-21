package com.marker55.apps.kui.test

import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test

public class BaseTestCase {
    
    def static final Logger logger = LoggerFactory.getLogger( BaseTestCase.class )
    
    def ApplicationContext context
    
    @BeforeSuite
    public void initialize() {
        try {
            context = new ClassPathXmlApplicationContext( [ "/kui-beans.xml" ] as String[] );
        }
        catch ( final Exception cause ) {
            logger.error( "Error while creating ApplicationContext.", cause );
        }
    }
    
    @AfterSuite
    public void destroy() {
        context = null
    }
    
    @Test
    public void testInitialization() {
        assert context != null
    }

}
