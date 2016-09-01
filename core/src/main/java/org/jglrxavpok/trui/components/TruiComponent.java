package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.TruiContext;
import org.joml.Vector2f;

/**
 * Base class representing any interface component. Sub classes must set preferred,
 * minimal and maximal sizes in their constructors.
 */
public class TruiComponent {

    private Vector2f position;
    private Vector2f minSize;
    private Vector2f maxSize;
    private Vector2f prefSize;
    private Vector2f size;
    private boolean dirty;
    private TruiComponent parent;
    private TruiContext context;

    public TruiComponent() {
        position = new Vector2f();
        minSize = new Vector2f();
        maxSize = new Vector2f();
        prefSize = new Vector2f();
        size = new Vector2f();
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getMinSize() {
        return minSize;
    }

    public Vector2f getMaxSize() {
        return maxSize;
    }

    public Vector2f getPreferredSize() {
        return prefSize;
    }

    public Vector2f getSize() {
        return size;
    }

    public TruiComponent setSize(Vector2f s) {
        return setSize(s.x, s.y);
    }

    public TruiComponent setSize(float w, float h) {
        size.set(w, h);
        return this;
    }

    /**
     * Sets the preferred size for this component
     * @param width
     *          The width of this component, in screen units
     * @param height
     *          The height of this component, in screen units
     * @return
     *          This component for chaining
     */
    public TruiComponent setPreferredSize(float width, float height) {
        prefSize.set(width, height);
        return this;
    }

    /**
     * Sets the preferred size for this component
     * @param size
     *          Vector containing width & height information
     * @return
     *          This component for chaining
     */
    public TruiComponent setPreferredSize(Vector2f size) {
        return setPreferredSize(size.x, size.y);
    }

    /**
     * Sets the maximal size for this component
     * @param width
     *          The width of this component, in screen units
     * @param height
     *          The height of this component, in screen units
     * @return
     *          This component for chaining
     */
    public TruiComponent setMaxSize(float width, float height) {
        maxSize.set(width, height);
        return this;
    }

    /**
     * Sets the maximal size for this component
     * @param size
     *          Vector containing width & height information
     * @return
     *          This component for chaining
     */
    public TruiComponent setMaxSize(Vector2f size) {
        return setMaxSize(size.x, size.y);
    }

    /**
     * Sets the minimal size for this component
     * @param width
     *          The width of this component, in screen units
     * @param height
     *          The height of this component, in screen units
     * @return
     *          This component for chaining
     */
    public TruiComponent setMinSize(float width, float height) {
        minSize.set(width, height);
        return this;
    }

    /**
     * Sets the minimal size for this component
     * @param size
     *          Vector containing width & height information
     * @return
     *          This component for chaining
     */
    public TruiComponent setMinSize(Vector2f size) {
        return setMinSize(size.x, size.y);
    }

    /**
     * Moves the component to a specific location on screen, expressed in screen coordinates.<br/>
     * One should know that the layout is completely free to override this location
     * @param pos
     *          The new position
     * @return
     *          This component for chaining
     */
    public TruiComponent setPosition(Vector2f pos) {
        return setPosition(pos.x, pos.y);
    }

    /**
     * Moves the component to a specific location on screen, expressed in screen coordinates.<br/>
     * One should know that the layout is completely free to override this location
     * @param x
     *          The position on the X axis
     * @param y
     *          The position on the Y axis
     * @return
     *          This component for chaining
     */
    public TruiComponent setPosition(float x, float y) {
        position.set(x, y);
        invalidate();
        return this;
    }

    /**
     * Invalidates this component in order to be redrawn whenever it is possible
     * @return this component for chaining
     */
    public TruiComponent invalidate() {
        dirty = true;
        return this;
    }

    /**
     * Returns true if this component is dirty and needs to be redrawn
     * @return
     *          true if the component is dirty
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Invalidates this component in order to be redrawn whenever it is possible,
     * also notifies the parent component (if any) that this component has changed in order to recompute the layout
     * @return this component for chaining
     */
    public TruiComponent invalidateHierarchy() {
        if(parent != null) {
            parent.invalidateHierarchy();
        }
        invalidate();
        return this;
    }

    /**
     * Returns the direct parent of this component, if any
     * @return
     *          The potential parent of this component
     */
    public TruiComponent getParent() {
        return parent;
    }

    protected void setParent(TruiComponent parent) {
        this.parent = parent;
    }

    protected void notifyContextHierarchyChange() {
        if(context != null)
            context.notifyHierarchyChange(this);
    }

    public void setContext(TruiContext context) {
        this.context = context;
    }

    public TruiContext getContext() {
        return context;
    }
}
