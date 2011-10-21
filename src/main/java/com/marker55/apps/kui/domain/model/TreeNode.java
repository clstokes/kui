package com.marker55.apps.kui.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.marker55.apps.kui.util.ObjectUtils;

/**
 * A model bean representing a node in a tree.
 * 
 * @author Cameron Stokes
 */
public class TreeNode {

    private String id;
    private String text;

    private boolean leaf;

    private List<TreeNode> children;

    /**
     * Adds the child node.
     * 
     * @param node the node
     */
    public void addChildNode( final TreeNode node ) {
        if ( children == null ) {
            children = new ArrayList<TreeNode>();
        }
        children.add( node );
    }

    /**
     * Gets the children.
     * 
     * @return the children
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the text.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Checks if is leaf.
     * 
     * @return true, if is leaf
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Sets the children.
     * 
     * @param children the new children
     */
    public void setChildren( final List<TreeNode> children ) {
        this.children = children;
    }

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId( final String id ) {
        this.id = id;
    }

    /**
     * Sets the leaf.
     * 
     * @param leaf the new leaf
     */
    public void setLeaf( final boolean leaf ) {
        this.leaf = leaf;
    }

    /**
     * Sets the text.
     * 
     * @param text the new text
     */
    public void setText( final String text ) {
        this.text = text;
    }

    /**
     * Returns a String representation of this object.
     * 
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return ObjectUtils.toString( this );
    }

}
