package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.layouts.AbsoluteLayout;
import org.jglrxavpok.trui.layouts.Layout;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Represents a component that is able to hold sub components.
 */
public class TruiPanel extends TruiComponent {

    private Layout layout;
    private List<TruiComponent> children;
    private Vector2f margins;

    /**
     * Creates a new empty panel with an {@link AbsoluteLayout}
     */
    public TruiPanel() {
        super();
        margins = new Vector2f(10);
        children = new ArrayList<TruiComponent>();
        layout = new AbsoluteLayout(this);
    }

    public Vector2f getMargins() {
        return margins;
    }

    public TruiPanel setMargins(Vector2f margins) {
        return setMargins(margins.x, margins.y);
    }

    public TruiPanel setMargins(float horizontal, float vertical) {
        margins.set(horizontal, vertical);
        invalidate();
        return this;
    }

    public List<TruiComponent> getChildren() {
        return children;
    }

    /**
     * Adds a subcomponent to this panel
     * @param component
     *          The subcomponent to add
     * @return
     *          The child for chaining
     */
    public <T extends TruiComponent> T addChild(T component) {
        if(children.contains(component))
            throw new IllegalStateException("A panel cannot hold the same component twice");
        component.setParent(this);
        component.setContext(getContext());
        children.add(component);
        layout.applyChildrenLayout();
        notifyContextHierarchyChange();
        invalidateHierarchy();
        return component;
    }

    public TruiPanel removeChild(TruiComponent component) {
        if(children.contains(component)) {
            component.setParent(null);
            component.setContext(null);
        }
        children.remove(component);
        layout.applyChildrenLayout();
        notifyContextHierarchyChange();
        return this;
    }

    public TruiPanel clear() {
        for(TruiComponent component : children) {
            component.setParent(null);
            component.setContext(null);
        }
        children.clear();
        layout.applyChildrenLayout();
        notifyContextHierarchyChange();
        return this;
    }

    /**
     * Sets the preferred size of this panel to the minimal size required by its children,
     * according to its {@link #getLayout() layout}.
     */
    public void pack() {
        layout.packChildren();
    }

    public Layout getLayout() {
        return layout;
    }

    @Override
    public TruiPanel invalidate() {
        layout.applyChildrenLayout();
        for (TruiComponent c : children) {
            c.invalidate();
        }
        super.invalidate();
        return this;
    }

    @Override
    public TruiPanel invalidateHierarchy() {
        for (TruiComponent c : children) {
            c.invalidate();
        }
        super.invalidateHierarchy();
        return this;
    }

    @Override
    public void setContext(TruiContext context) {
        super.setContext(context);
        for (TruiComponent c : children) {
            c.setContext(context);
        }
    }

    /**
     * Returns itself for chaining
     * @return
     *          This panel for chaining
     */
    public TruiPanel setLayout(Layout newLayout) {
        layout = newLayout;
        return this;
    }
}
