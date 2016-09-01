package org.jglrxavpok.trui.layouts;

import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;

public class AbsoluteLayout extends Layout {

    public AbsoluteLayout(TruiPanel owner) {
        super(owner);
    }

    public void applyChildrenLayout() {
        for(TruiComponent c : children) {
            c.setSize(c.getPreferredSize());
        }

        owner.invalidate();
    }
}
