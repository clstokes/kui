package com.marker55.apps.kui.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ObjectName;

import com.marker55.apps.kui.domain.DomainException;
import com.marker55.apps.kui.domain.model.MBean;
import com.marker55.apps.kui.domain.model.MBeanAttribute;
import com.marker55.apps.kui.domain.model.MBeanAttributeComparator;
import com.marker55.apps.kui.domain.model.MBeanOperation;
import com.marker55.apps.kui.domain.model.MBeanOperationComparator;
import com.marker55.apps.kui.domain.model.TreeNode;
import com.marker55.apps.kui.domain.model.TreeNodeComparator;
import com.marker55.apps.kui.util.JMXUtils;
import com.marker55.apps.kui.util.ObjectUtils;
import com.marker55.apps.kui.util.StringUtils;
import com.marker55.apps.kui.util.logging.Logger;
import com.marker55.apps.kui.util.logging.LoggerFactory;

/**
 * Service class for JMX operations.
 * 
 * @author Cameron Stokes
 */
public class JMXService {

    private static final Logger logger = LoggerFactory.getLogger( JMXService.class );

    private final MBeanAttributeConverter attributeConverter;

    /**
     * Instantiates a new JMXService.
     * 
     * @param attributeConverter the {@link MBeanAttributeConverter}
     */
    public JMXService( final MBeanAttributeConverter attributeConverter ) {
        this.attributeConverter = attributeConverter;
    }

    /**
     * Adds the JMX domains as top-level {@link TreeNode} values.
     * 
     * @param mbeans the mbeans
     * @param nodeList the node list
     */
    private void addDomains( final Set<ObjectName> mbeans, final List<TreeNode> nodeList ) {
        for ( final ObjectName mbean : mbeans ) {

            final String domain = mbean.getDomain();

            if ( findNode( nodeList, domain ) != null ) {
                continue;
            }

            final TreeNode node = new TreeNode();
            node.setId( domain );
            node.setText( domain );

            nodeList.add( node );
        }
    }

    /**
     * Adds the given {@link Set} of {@link ObjectName} objects to the
     * {@link TreeNode} {@link List} as children to the correct {@link TreeNode}
     * .
     * 
     * @param mbeans the mbeans
     * @param nodeList the node list
     */
    private void addNodes( final Set<ObjectName> mbeans, final List<TreeNode> nodeList ) {

        for ( final ObjectName mbean : mbeans ) {
            final String domain = mbean.getDomain();
            final Map<String, String> keyPropertyMap = JMXUtils.getMBeanKeyPropertyMap( mbean );

            TreeNode parentNode = findNode( nodeList, domain );

            final Iterator<Map.Entry<String, String>> iterator = keyPropertyMap.entrySet().iterator();
            Map.Entry<String, String> currentEntry = iterator.next();
            while ( iterator.hasNext() ) {
                TreeNode childNode = findNode( parentNode.getChildren(), currentEntry.getValue() );
                if ( childNode == null ) {
                    childNode = new TreeNode();
                    childNode.setId( currentEntry.getValue() );
                    childNode.setText( currentEntry.getValue() );

                    parentNode.addChildNode( childNode );
                }
                parentNode = childNode;
                currentEntry = iterator.next();
            }

            final TreeNode leafNode = new TreeNode();
            leafNode.setId( mbean.getCanonicalName() );
            leafNode.setText( currentEntry.getValue() );
            leafNode.setLeaf( true );

            parentNode.addChildNode( leafNode );

        }
    }

    /**
     * Finds the specified node in the {@link List} of {@link TreeNode} objects.
     * 
     * @param nodeList the node list
     * @param node the node
     * @return the tree node
     */
    private TreeNode findNode( final List<TreeNode> nodeList, final String node ) {

        if ( ObjectUtils.isEmpty( nodeList ) ) {
            return null;
        }

        for ( final TreeNode treeNode : nodeList ) {
            if ( StringUtils.equals( treeNode.getText(), node ) ) {
                return treeNode;
            }
        }
        return null;
    }

    /**
     * Gets the attribute converter.
     * 
     * @return the attribute converter
     */
    public MBeanAttributeConverter getAttributeConverter() {
        return attributeConverter;
    }

    /**
     * Converts the given value based on the specified type.
     * 
     * @param attributeValue the attribute value
     * @param parameterType the parameter type
     * @return the converted value
     * @throws DomainException the domain exception
     */
    private Object getConvertedValue( final String attributeValue, final String parameterType ) throws DomainException {
        Object convertedValue = null;

        if ( StringUtils.equals( "java.lang.String", parameterType ) ) {
            convertedValue = attributeValue;
        }
        else if ( StringUtils.equals( parameterType, "boolean" ) ) {
            convertedValue = getAttributeConverter().convertToBoolean( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "byte" ) ) {
            convertedValue = getAttributeConverter().convertToByte( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "char" ) ) {
            convertedValue = getAttributeConverter().convertToChar( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "double" ) ) {
            convertedValue = getAttributeConverter().convertToDouble( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "float" ) ) {
            convertedValue = getAttributeConverter().convertToFloat( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "int" ) ) {
            convertedValue = getAttributeConverter().convertToInt( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "long" ) ) {
            convertedValue = getAttributeConverter().convertToLong( attributeValue );
        }
        else if ( StringUtils.equals( parameterType, "short" ) ) {
            convertedValue = getAttributeConverter().convertToShort( attributeValue );
        }
        else {
            throw new DomainException( "Unsupported type [" + parameterType + "] for value [" + attributeValue + "]" );
        }

        return convertedValue;
    }

    /**
     * Retrieves the requested MBean from the MBeanServer and converts to an
     * application friendly model bean.
     * 
     * @param mbeanName the mbean name
     * @return the {@link MBean}
     * @throws DomainException the domain exception
     */
    public MBean getMBean( final String mbeanName ) throws DomainException {
        try {
            final MBeanInfo mbeanInfo = JMXUtils.getMBeanInfo( mbeanName );

            final Map<String, String> keyPropertyMap = JMXUtils.getMBeanKeyPropertyMap( mbeanName );
            final Iterator<Map.Entry<String, String>> mapIterator = keyPropertyMap.entrySet().iterator();

            Map.Entry<String, String> currentEntry = null;
            Map.Entry<String, String> previousEntry = null;

            while ( mapIterator.hasNext() ) {
                currentEntry = mapIterator.next();
                previousEntry = currentEntry;
            }

            final MBean mbean = new MBean();
            mbean.setDisplayName( previousEntry.getValue() );
            mbean.setName( mbeanName );
            mbean.setClassName( mbeanInfo.getClassName() );
            mbean.setDescription( mbeanInfo.getDescription() );

            for ( final MBeanAttributeInfo attributeInfo : mbeanInfo.getAttributes() ) {
                final String attributeName = attributeInfo.getName();

                final MBeanAttribute mbeanAttribute = new MBeanAttribute();
                mbeanAttribute.setName( attributeName );

                try {
                    final Object attributeValue = JMXUtils.getMBeanAttribute( mbeanName, attributeName );
                    mbeanAttribute.setType( attributeInfo.getType() );
                    mbeanAttribute.setValue( attributeValue != null ? attributeValue.toString() : "" );
                    mbeanAttribute.setAvailable( true );
                    mbeanAttribute.setWritable( attributeInfo.isWritable() );
                }
                catch ( final Throwable throwable ) {
                    logger.debug( "Attribute [{}] unavailable for MBean [{}].", attributeName, mbeanName );
                    mbeanAttribute.setAvailable( false );
                }

                mbean.addAttribute( mbeanAttribute );
            }

            sortMBeanAttributes( mbean.getAttributes() );

            for ( final MBeanOperationInfo operationInfo : mbeanInfo.getOperations() ) {
                final MBeanOperation mbeanOperation = new MBeanOperation();
                mbeanOperation.setName( operationInfo.getName() );
                mbeanOperation.setReturnType( operationInfo.getReturnType() );

                for ( final MBeanParameterInfo parameter : operationInfo.getSignature() ) {
                    mbeanOperation.addParameter( parameter.getType() );
                }

                mbean.addOperation( mbeanOperation );
            }

            sortMBeanOperations( mbean.getOperations() );

            return mbean;
        }
        catch ( final Throwable cause ) {
            throw new DomainException( "ApplicationMessage while getting MBeanInfo for [" + mbeanName + "].", cause );
        }
    }

    /**
     * Retrieves the array of available MBeans from the MBeanServer and converts
     * them to an UI specific representation.
     * <p>
     * TODO: This should return a UI-agnostic representation.
     * </p>
     * 
     * @return array of {@link TreeNode}
     * @throws DomainException the domain exception
     */
    public TreeNode[] getMBeanArray() throws DomainException {

        final List<TreeNode> nodeList = new ArrayList<TreeNode>();

        Set<ObjectName> mbeans;

        try {

            mbeans = JMXUtils.getMBeans( "*:*" );

            addDomains( mbeans, nodeList );
            addNodes( mbeans, nodeList );
            sortNodes( nodeList );

        }
        catch ( final Throwable cause ) {
            throw new DomainException( "ApplicationMessage while retrieving MBean list.", cause );
        }

        return nodeList.toArray( new TreeNode[nodeList.size()] );
    }

    /**
     * Invokes the given operation with parameters on the request mbean.
     * 
     * @param mbeanName the mbean name
     * @param operationName the operation name
     * @param parameters the parameters
     * @return the {@link Object}
     * @throws DomainException the domain exception
     */
    public Object invokeOperation( final String mbeanName, final String operationName, final String[] parameters )
            throws DomainException {
        try {
            final MBean mbean = getMBean( mbeanName );
            MBeanOperation operation = null;
            for ( final MBeanOperation currentOperation : mbean.getOperations() ) {
                if ( !StringUtils.equals( operationName, currentOperation.getName() ) ) {
                    continue;
                }
                operation = currentOperation;
            }

            if ( operation == null ) {
                throw new DomainException( "Operation not specified for mbean [" + mbeanName + "]" );
            }

            final int parameterListSize = ObjectUtils.sizeOf( operation.getParameters() );
            final List<String> parameterTypeList = new ArrayList<String>( parameterListSize );
            final List<Object> parameterList = new ArrayList<Object>( parameterListSize );
            if ( parameterListSize > 0 ) {
                int i = 0;
                for ( final String parameterType : operation.getParameters() ) {
                    parameterTypeList.add( parameterType );
                    parameterList.add( getConvertedValue( parameters[i], parameterType ) );
                    i++;
                }
            }

            return JMXUtils.invokeOperation( mbeanName, operationName, parameterList
                    .toArray( new Object[parameterListSize] ), parameterTypeList
                    .toArray( new String[parameterListSize] ) );
        }
        catch ( final Throwable cause ) {
            throw new DomainException( "Error while invoking operation [" + operationName + "] on mbean [" + mbeanName
                    + "]", cause );
        }
    }

    /**
     * Sets the given attribute and value on the given MBean.
     * 
     * @param mbeanName the mbean name
     * @param attributeName the attribute name
     * @param attributeValue the attribute value
     * @throws DomainException the domain exception
     */
    public void setMBeanAttribute( final String mbeanName, final String attributeName, final String attributeValue )
            throws DomainException {
        try {
            final MBean mbean = getMBean( mbeanName );
            MBeanAttribute attribute = null;
            for ( final MBeanAttribute currentAttribute : mbean.getAttributes() ) {
                if ( !StringUtils.equals( attributeName, currentAttribute.getName() ) ) {
                    continue;
                }
                attribute = currentAttribute;
            }

            if ( attribute == null ) {
                throw new DomainException( "Unknown attribute [" + attributeName + "] specified for mbean ["
                        + mbeanName + "]" );
            }

            final Object convertedValue = getConvertedValue( attributeValue, attribute.getType() );

            JMXUtils.setMBeanAttribute( mbeanName, attributeName, convertedValue );
        }
        catch ( final Throwable cause ) {
            throw new DomainException( "Error while setting attribute [" + attributeName + "] to value ["
                    + attributeValue + "] on mbean [" + mbeanName + "]", cause );
        }

    }

    /**
     * Sort the {@link MBeanAttribute} {@link List}.
     * 
     * @param attributes the {@link MBeanAttribute} {@link List}
     */
    private void sortMBeanAttributes( final List<MBeanAttribute> attributes ) {
        ObjectUtils.sortList( attributes, new MBeanAttributeComparator() );
    }

    /**
     * Sort the {@link MBeanOperation} {@link List}.
     * 
     * @param operations the {@link MBeanOperation} {@link List}
     */
    private void sortMBeanOperations( final List<MBeanOperation> operations ) {
        ObjectUtils.sortList( operations, new MBeanOperationComparator() );
    }

    /**
     * Sort the {@link TreeNode} {@link List}.
     * 
     * @param children the {@link TreeNode} {@link List}
     */
    private void sortNodes( final List<TreeNode> children ) {
        ObjectUtils.sortList( children, new TreeNodeComparator() );

        if ( ObjectUtils.isEmpty( children ) ) {
            return;
        }

        for ( final TreeNode treeNode : children ) {
            sortNodes( treeNode.getChildren() );
        }
    }

}
