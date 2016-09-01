package org.jglrxavpok.trui.layouts;

import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;

import java.util.List;

/**
 * Layouts are used to avoid hardcoding the position of each component
 */
public abstract class Layout {
    protected final TruiPanel owner;
    protected final List<TruiComponent> children;

    public Layout(TruiPanel owner) {
        this.owner = owner;
        children = owner.getChildren();
    }

    public TruiPanel getOwner() {
        return owner;
    }

    public void packChildren() {
        applyChildrenLayout();
        float minX = Float.POSITIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        for(TruiComponent c : children) {
            if(c.getPosition().x < minX)
                minX = c.getPosition().x;

            if(c.getPosition().y < minY)
                minY = c.getPosition().y;

            if(c.getPosition().x+c.getSize().x > maxX)
                maxX = c.getPosition().x+c.getSize().x;

            if(c.getPosition().y+c.getSize().y > maxY)
                maxY = c.getPosition().y+c.getSize().y;
        }

        float width = maxX - minX;
        float height = maxY - minY;
        owner.setPreferredSize(width, height);
        owner.invalidateHierarchy();
    }

    public abstract void applyChildrenLayout();
}
