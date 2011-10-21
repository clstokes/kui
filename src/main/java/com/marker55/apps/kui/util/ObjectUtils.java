package com.marker55.apps.kui.util;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.marker55.apps.kui.BaseException;

/**
 * Utility methods for working with various objects.
 * 
 * @author Cameron Stokes
 */
public final class ObjectUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private ObjectUtils() {

    }

    /**
     * Gets the object as json string.
     * 
     * @param object the object
     * @return the object as json string
     */
    public static String getObjectAsJSONString( final Object object ) {
        if ( ObjectUtils.isArray( object ) ) {
            return JSONArray.fromObject( object ).toString();
        }

        return JSONObject.fromObject( object ).toString();
    }

    /**
     * <p>
     * Instantiates an object of the given class name using the default,
     * no-argument constructor.
     * </p>
     * 
     * @param className name of class to instantiate
     * @return a new object of the given class name
     * @throws BaseException thrown if an unexpected error occurs
     */
    public static Object instantiate( final String className ) throws BaseException {
        if ( org.apache.commons.lang.StringUtils.isEmpty( className ) ) {
            return null;
        }

        final Class<?>[] classList = new Class<?>[0];
        final Object[] paramList = new Object[0];

        return instantiate( className, classList, paramList );
    }

    /**
     * <p>
     * Instantiates an object of the given class name using the constructor
     * matching the given class list.
     * </p>
     * 
     * @param className name of class to instantiate
     * @param constructClassList list of the classes of the constructor
     * @param constructorParamList list of object matching the class list
     * @return a new object of the given class name
     * @throws BaseException thrown if an unexpected error occurs
     */
    public static Object instantiate( final String className, final Class<?>[] constructClassList,
            final Object[] constructorParamList ) throws BaseException {
        if ( org.apache.commons.lang.StringUtils.isEmpty( className ) ) {
            return null;
        }

        if ( ( constructClassList == null ) || ( constructorParamList == null ) ) {
            throw new BaseException( StringUtils.append( "Unable to instantiate class [", className,
                    "] with null class and object list." ) );
        }
        else if ( constructClassList.length != constructorParamList.length ) {
            throw new BaseException( StringUtils.append( "Unable to instantiate class [", className,
                    "] Size of class list does not match size of parameter list." ) );
        }

        Class<?> clazz = null;

        try {
            clazz = Class.forName( className );
        }
        catch ( final Throwable ex ) {
            throw new BaseException( StringUtils.append( "Unable to get reference to class [", className, "]" ), ex );
        }

        Constructor<?> constructor = null;
        try {
            constructor = clazz.getConstructor( constructClassList );
        }
        catch ( final Throwable ex ) {
            throw new BaseException( StringUtils.append( "Unable to get constructor for class [", className, "]" ), ex );
        }

        Object newObject = null;
        try {
            newObject = constructor.newInstance( constructorParamList );
        }
        catch ( final Throwable ex ) {
            throw new BaseException( StringUtils.append( "Unable to instantiate class [", className, "]" ), ex );
        }

        return newObject;
    }

    /**
     * Checks if the given {@link Object} is an array.
     * 
     * @param object the object
     * @return true, if is array
     */
    public static boolean isArray( final Object object ) {
        if ( object == null ) {
            return false;
        }
        return object.getClass().isArray();
    }

    /**
     * <p>
     * Checks the given list for elements.
     * </p>
     * 
     * @param collection collection to check for contents
     * @return boolean flag indicating the given List is empty or not.
     */
    public static boolean isEmpty( final Collection<?> collection ) {
        return ( collection == null ) || ( collection.size() == 0 );
    }

    /**
     * <p>
     * Checks the given list for elements.
     * </p>
     * 
     * @param map map to check for contents
     * @return boolean flag indicating the given List is empty or not.
     */
    public static boolean isEmpty( final Map<?, ?> map ) {
        return ( map == null ) || ( map.size() == 0 );
    }

    /**
     * <p>
     * Checks the given array for elements.
     * </p>
     * 
     * @param objects object array to check for contents
     * @return boolean flag indicating the given array is empty or not.
     */
    public static boolean isEmpty( final Object[] objects ) {
        return ( objects == null ) || ( objects.length == 0 );
    }

    /**
     * <p>
     * Checks the given list for elements.
     * </p>
     * 
     * @param collection collection to check for contents
     * @return boolean flag indicating the given List is not empty
     */
    public static boolean isNotEmpty( final Collection<?> collection ) {
        return !isEmpty( collection );
    }

    /**
     * <p>
     * Checks the given list for elements.
     * </p>
     * 
     * @param map map to check for contents
     * @return boolean flag indicating the given List is not empty
     */
    public static boolean isNotEmpty( final Map<?, ?> map ) {
        return !isEmpty( map );
    }

    /**
     * <p>
     * Checks the given array for elements.
     * </p>
     * 
     * @param objects object array to check for contents
     * @return boolean flag indicating the given array is not empty
     */
    public static boolean isNotEmpty( final Object[] objects ) {
        return !isEmpty( objects );
    }

    /**
     * <p>
     * Returns the size of the given {@link Collection}. Returns 0 if
     * {@link Collection} is null or empty.
     * </p>
     * 
     * @param collection {@link Collection} to check
     * @return size of given {@link List}
     */
    public static int sizeOf( final Collection<?> collection ) {
        if ( isEmpty( collection ) ) {
            return 0;
        }

        return collection.size();
    }

    /**
     * <p>
     * Returns the size of the given array. Returns 0 if array is null or empty.
     * </p>
     * 
     * @param objects array to check
     * @return size of given array
     */
    public static int sizeOf( final Object[] objects ) {
        if ( isEmpty( objects ) ) {
            return 0;
        }

        return objects.length;
    }

    /**
     * Sorts the given {@link List} with the given {@link Comparator}.
     * 
     * @param list the list
     * @param comparator the comparator
     */
    @SuppressWarnings ( "unchecked" )
    public static void sortList( final List list, final Comparator comparator ) {
        if ( ObjectUtils.isEmpty( list ) ) {
            return;
        }

        Collections.sort( list, comparator );
    }

    /**
     * Returns a String representation of the given object.
     * 
     * @param object - object to build a string off of
     * @return String representation
     */
    public static String toString( final Object object ) {
        final StandardToStringStyle toStringStyle = new StandardToStringStyle();
        toStringStyle.setUseShortClassName( true );
        return ToStringBuilder.reflectionToString( object, toStringStyle );
    }

}
