package com.marker55.apps.kui.util;

import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.management.Attribute;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.marker55.apps.kui.BaseException;

/**
 * Utility methods for JMX operations.
 * 
 * @author Cameron Stokes
 */
public final class JMXUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private JMXUtils() {

    }

    /**
     * Gets all available MBean domains.
     * 
     * @return all MBean domains
     * @throws BaseException the base exception
     */
    public static Set<String> getAllMBeanDomains() throws BaseException {
        try {
            final String[] domains = getMBeanServer().getDomains();
            final Set<String> domainSet = new HashSet<String>();
            for ( final String domain : domains ) {
                domainSet.add( domain );
            }
            return domainSet;
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "ApplicationMessage while getting MBean set.", cause );
        }
    }

    /**
     * Gets all the available MBeans.
     * 
     * @return all available MBeans
     * @throws BaseException the base exception
     */
    public static Set<ObjectName> getAllMBeans() throws BaseException {
        try {
            return getMBeanServer().queryNames( new ObjectName( "*:*" ), null );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "ApplicationMessage while getting MBean set.", cause );
        }
    }

    /**
     * Gets the MBean attribute.
     * 
     * @param mbeanName the mbean name
     * @param attribute the attribute
     * @return the m bean attribute
     * @throws BaseException the base exception
     */
    public static Object getMBeanAttribute( final String mbeanName, final String attribute ) throws BaseException {
        try {
            return getMBeanServer().getAttribute( ObjectName.getInstance( mbeanName ), attribute );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "ApplicationMessage while getting attribute [" + attribute + "] for mbean ["
                    + mbeanName + "].", cause );
        }
    }

    /**
     * Gets the {@link MBeanInfo} for the requested MBean.
     * 
     * @param mbeanName the mbean name
     * @return the m bean info
     * @throws BaseException the base exception
     */
    public static MBeanInfo getMBeanInfo( final String mbeanName ) throws BaseException {
        try {
            return getMBeanServer().getMBeanInfo( ObjectName.getInstance( mbeanName ) );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "ApplicationMessage while getting MBeanInfo for [" + mbeanName + "].", cause );
        }
    }

    /**
     * Gets the MBean keys and values as a {@link Map}
     * 
     * @param objectName the object name
     * @return the MBean key property map
     */
    public static Map<String, String> getMBeanKeyPropertyMap( final ObjectName objectName ) {
        if ( objectName == null ) {
            return null;
        }

        return getMBeanKeyPropertyMap( objectName.getKeyPropertyListString() );

    }

    /**
     * Gets the MBean keys and values as a {@link Map}
     * 
     * @param objectName the object name
     * @return the MBean key property map
     */
    public static Map<String, String> getMBeanKeyPropertyMap( final String objectName ) {
        if ( org.apache.commons.lang.StringUtils.isEmpty( objectName ) ) {
            return null;
        }

        final String[] keyPairs = org.apache.commons.lang.StringUtils.split( objectName, "," );

        final Map<String, String> keyPropertyMap = new LinkedHashMap<String, String>( ObjectUtils.sizeOf( keyPairs ) );

        for ( final String keyPair : keyPairs ) {
            final String[] keyAndProperty = org.apache.commons.lang.StringUtils.split( keyPair, "=" );
            keyPropertyMap.put( keyAndProperty[0], keyAndProperty[1] );
        }

        return keyPropertyMap;

    }

    /**
     * Gets all the available MBeans based on the given query.
     * 
     * @param query the query
     * @return all available MBeans
     * @throws BaseException the base exception
     */
    public static Set<ObjectName> getMBeans( final String query ) throws BaseException {
        try {
            return getMBeanServer().queryNames( new ObjectName( query ), null );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "ApplicationMessage while getting MBean set.", cause );
        }
    }

    /**
     * Gets the MBeanServer.
     * 
     * @return the {@link MBeanServer}
     * @throws BaseException the base exception
     */
    public static MBeanServer getMBeanServer() throws BaseException {
        try {
            return ManagementFactory.getPlatformMBeanServer();
        }
        catch ( final Throwable ex ) {
            throw new BaseException( "Unable to get MBeanServer.", ex );
        }
    }

    /**
     * Invokes the specified operation on the specified MBean.
     * 
     * @param mbeanName the mbean name
     * @param operationName the operation name
     * @param parameters the parameters
     * @param signature the signature
     * @return the object
     * @throws BaseException the base exception
     */
    public static Object invokeOperation( final String mbeanName, final String operationName,
            final Object[] parameters, final String[] signature ) throws BaseException {
        try {
            final ObjectName objectName = new ObjectName( mbeanName );
            return getMBeanServer().invoke( objectName, operationName, parameters, signature );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "Error while invoking operation [" + operationName + "] on mbean [" + mbeanName
                    + "]", cause );
        }
    }

    /**
     * Sets the MBean attribute.
     * 
     * @param mbeanName the mbean name
     * @param attributeName the attribute name
     * @param attributeValue the attribute value
     * @throws BaseException the base exception
     */
    public static void setMBeanAttribute( final String mbeanName, final String attributeName,
            final Object attributeValue ) throws BaseException {
        try {
            final ObjectName objectName = new ObjectName( mbeanName );
            final Attribute attribute = new Attribute( attributeName, attributeValue );
            getMBeanServer().setAttribute( objectName, attribute );
        }
        catch ( final Throwable cause ) {
            throw new BaseException( "Error while setting attribute [" + attributeName + "] to value ["
                    + attributeValue + "] on mbean [" + mbeanName + "]", cause );
        }
    }

}
