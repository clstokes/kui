package com.marker55.apps.kui.domain.service;

import org.apache.commons.beanutils.Converter;

import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * A utility class to convert values between different types.
 * 
 * @author Cameron Stokes
 */
public class MBeanAttributeConverter {

    private static final Logger logger = LoggerFactory.getLogger( MBeanAttributeConverter.class );

    private Converter booleanConverter;
    private Converter byteConverter;
    private Converter characterConverter;
    private Converter doubleConverter;
    private Converter floatConverter;
    private Converter integerConverter;
    private Converter longConverter;
    private Converter shortConverter;

    /**
     * Convert to boolean.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToBoolean( final String value ) {

        Object returnObject = getBooleanConverter().convert( Boolean.class, value );

        logger.debug( "Converted [" + value + "] to Boolean [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to byte.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToByte( final String value ) {

        Object returnObject = getByteConverter().convert( Byte.class, value );

        logger.debug( "Converted [" + value + "] to Byte [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to char.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToChar( final String value ) {

        Object returnObject = getCharacterConverter().convert( Character.class, value );

        logger.debug( "Converted [" + value + "] to char [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to double.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToDouble( final String value ) {
        Object returnObject = getDoubleConverter().convert( Double.class, value );

        logger.debug( "Converted [" + value + "] to double [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to float.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToFloat( final String value ) {

        Object returnObject = getFloatConverter().convert( Float.class, value );

        logger.debug( "Converted [" + value + "] to float [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to int.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToInt( final String value ) {
        Object returnObject = getIntegerConverter().convert( Integer.class, value );

        logger.debug( "Converted [" + value + "] to integer [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to long.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToLong( final String value ) {
        Object returnObject = getLongConverter().convert( Long.class, value );

        logger.debug( "Converted [" + value + "] to long [" + returnObject + "].", value, returnObject );

        return returnObject;
    }

    /**
     * Convert to short.
     * 
     * @param value the value to convert
     * @return the converted {@link Object}
     */
    public Object convertToShort( final String value ) {
        Object returnObject = getShortConverter().convert( Short.class, value );

        logger.debug( "Converted [" + value + "] to short [" + returnObject + "].", value, returnObject );

        return returnObject;

    }

    /**
     * Gets the boolean converter.
     * 
     * @return the boolean converter
     */
    public Converter getBooleanConverter() {
        return booleanConverter;
    }

    /**
     * Gets the byte converter.
     * 
     * @return the byte converter
     */
    public Converter getByteConverter() {
        return byteConverter;
    }

    /**
     * Gets the character converter.
     * 
     * @return the character converter
     */
    public Converter getCharacterConverter() {
        return characterConverter;
    }

    /**
     * Gets the double converter.
     * 
     * @return the double converter
     */
    public Converter getDoubleConverter() {
        return doubleConverter;
    }

    /**
     * Gets the float converter.
     * 
     * @return the float converter
     */
    public Converter getFloatConverter() {
        return floatConverter;
    }

    /**
     * Gets the integer converter.
     * 
     * @return the integer converter
     */
    public Converter getIntegerConverter() {
        return integerConverter;
    }

    /**
     * Gets the long converter.
     * 
     * @return the long converter
     */
    public Converter getLongConverter() {
        return longConverter;
    }

    /**
     * Gets the short converter.
     * 
     * @return the long converter
     */
    public Converter getShortConverter() {
        return shortConverter;
    }

    /**
     * Sets the boolean converter.
     * 
     * @param booleanConverter the new boolean converter
     */
    public void setBooleanConverter( final Converter booleanConverter ) {
        this.booleanConverter = booleanConverter;
    }

    /**
     * Sets the byte converter.
     * 
     * @param byteConverter the new byte converter
     */
    public void setByteConverter( final Converter byteConverter ) {
        this.byteConverter = byteConverter;
    }

    /**
     * Sets the character converter.
     * 
     * @param charConverter the new character converter
     */
    public void setCharacterConverter( final Converter characterConverter ) {
        this.characterConverter = characterConverter;
    }

    /**
     * Sets the double converter.
     * 
     * @param doubleConverter the new double converter
     */
    public void setDoubleConverter( final Converter doubleConverter ) {
        this.doubleConverter = doubleConverter;
    }

    /**
     * Sets the float converter.
     * 
     * @param floatConverter the new float converter
     */
    public void setFloatConverter( final Converter floatConverter ) {
        this.floatConverter = floatConverter;
    }

    /**
     * Sets the integer converter.
     * 
     * @param intConverter the new integer converter
     */
    public void setIntegerConverter( final Converter integerConverter ) {
        this.integerConverter = integerConverter;
    }

    /**
     * Sets the long converter.
     * 
     * @param longConverter the new long converter
     */
    public void setLongConverter( final Converter longConverter ) {
        this.longConverter = longConverter;
    }

    /**
     * Sets the short converter.
     * 
     * @param shortConverter the new short converter
     */
    public void setShortConverter( final Converter shortConverter ) {
        this.shortConverter = shortConverter;
    }

}
