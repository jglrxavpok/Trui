package org.jglrxavpok.trui.components;

public class TruiScreen extends TruiPanel {

    public TruiScreen() {
        super();
    }

    @Override
    public TruiScreen invalidate() {
        return this;
    }

    @Override
    public TruiPanel invalidateHierarchy() {
        getLayout().applyChildrenLayout();
        return this;
    }

    public void hide() {

    }

    public void show() {

    }
}
