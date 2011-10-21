package com.marker55.apps.kui.test;

/**
 * An MBean to test various capabilities of the application.
 * 
 * @author Cameron Stokes
 */
public class TestMBean {

    private boolean booleanValue;
    private byte byteValue;
    private char charValue;
    private double doubleValue;
    private float floatValue;
    private int intValue;
    private long longValue;
    private short shortValue;
    private String stringValue;

    private String multipleStringValue;
    private boolean multipleBooleanValue;

    /**
     * Gets the boolean value.
     * 
     * @return the boolean value
     */
    public boolean getBooleanValue() {
        return booleanValue;
    }

    /**
     * Gets the byte value.
     * 
     * @return the byte value
     */
    public byte getByteValue() {
        return byteValue;
    }

    /**
     * Gets the char value.
     * 
     * @return the char value
     */
    public char getCharValue() {
        return charValue;
    }

    /**
     * Gets the double value.
     * 
     * @return the double value
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Gets the float value.
     * 
     * @return the float value
     */
    public float getFloatValue() {
        return floatValue;
    }

    /**
     * Gets the int value.
     * 
     * @return the int value
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Gets the long value.
     * 
     * @return the long value
     */
    public long getLongValue() {
        return longValue;
    }

    /**
     * Gets the multiple values.
     * 
     * @return the multiple values
     */
    public String getMultipleValues() {
        return "multipleStringValue: " + multipleStringValue + ", multipleBooleanValue: " + multipleBooleanValue;
    }

    /**
     * Gets the short value.
     * 
     * @return the short value
     */
    public short getShortValue() {
        return shortValue;
    }

    /**
     * Gets the string value.
     * 
     * @return the string value
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Sets the boolean value.
     * 
     * @param booleanValue the new boolean value
     */
    public void setBooleanValue( final boolean booleanValue ) {
        this.booleanValue = booleanValue;
    }

    /**
     * Sets the byte value.
     * 
     * @param byteValue the new byte value
     */
    public void setByteValue( final byte byteValue ) {
        this.byteValue = byteValue;
    }

    /**
     * Sets the char value.
     * 
     * @param charValue the new char value
     */
    public void setCharValue( final char charValue ) {
        this.charValue = charValue;
    }

    /**
     * Sets the double value.
     * 
     * @param doubleValue the new double value
     */
    public void setDoubleValue( final double doubleValue ) {
        this.doubleValue = doubleValue;
    }

    /**
     * Sets the float value.
     * 
     * @param floatValue the new float value
     */
    public void setFloatValue( final float floatValue ) {
        this.floatValue = floatValue;
    }

    /**
     * Sets the int value.
     * 
     * @param intValue the new int value
     */
    public void setIntValue( final int intValue ) {
        this.intValue = intValue;
    }

    /**
     * Sets the long value.
     * 
     * @param longValue the new long value
     */
    public void setLongValue( final long longValue ) {
        this.longValue = longValue;
    }

    /**
     * Sets the multiple values.
     * 
     * @param stringValue the string value
     * @param booleanValue the boolean value
     */
    public void setMultipleValues( final String stringValue, final boolean booleanValue ) {
        multipleStringValue = stringValue;
        multipleBooleanValue = booleanValue;
    }

    /**
     * Sets the string value.
     * 
     * @param stringValue the new string value
     */
    public void setStringValue( final String stringValue ) {
        this.stringValue = stringValue;
    }

    /**
     * Sets the short value.
     * 
     * @param shortValue the new short value
     */
    public void setShortValue( final short shortValue ) {
        this.shortValue = shortValue;
    }

    /**
     * Sets lots of values.
     * 
     * @param booleanValue the boolean value
     * @param byteValue the byte value
     * @param charValue the char value
     * @param doubleValue the double value
     */
    public void setLotsOfValues( final boolean booleanValue, final byte byteValue, final char charValue,
            final double doubleValue ) {

    }

}
