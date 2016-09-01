package org.jglrxavpok.trui.testui;

import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.components.TruiScreen;

public class TestScreen extends TruiScreen {

    @Override
    public void show() {
        clear();
        TruiLabel label = addChild(new TruiLabel("Test label", getContext().getFont("Consolas", 12)));
        label.setPosition(getSize().x/2f, getSize().y/2f);
        label.getPosition().sub(label.getPreferredSize().x/2f, label.getPreferredSize().y/2f);
    }
}
