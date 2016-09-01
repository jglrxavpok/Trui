package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.layouts.AbsoluteLayout;
import org.jglrxavpok.trui.layouts.Layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Represents a component that is able to hold sub components.
 */
public class TruiPanel extends TruiComponent {

    private Layout layout;
    private List<TruiComponent> children;

    /**
     * Creates a new empty panel with an {@link AbsoluteLayout}
     */
    public TruiPanel() {
        super();
        children = new ArrayList<TruiComponent>();
        layout = new AbsoluteLayout(this);
    }

    public List<TruiComponent> getChildren() {
        return children;
    }

    /**
     * Adds a subcomponent to this panel
     * @param component
     *          The subcomponent to add
     * @return
     *          This panel for chaining
     */
    public TruiPanel addChild(TruiComponent component) {
        if(children.contains(component))
            throw new IllegalStateException("A panel cannot hold the same component twice");
        component.setParent(this);
        component.setContext(getContext());
        children.add(component);
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
        for (TruiComponent c : children) {
            c.invalidate();
        }
        return this;
    }

    @Override
    public TruiPanel invalidateHierarchy() {
        super.invalidateHierarchy();
        layout.applyChildrenLayout();
        return this;
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
