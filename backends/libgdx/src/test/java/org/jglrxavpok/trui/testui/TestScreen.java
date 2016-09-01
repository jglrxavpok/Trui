package org.jglrxavpok.trui.testui;

import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.components.TruiScreen;

public class TestScreen extends TruiScreen {

    @Override
    public void show() {
        clear();
        TruiLabel label1 = addChild(new TruiLabel("Test label", getContext().getFont("Consolas", 12)));
        label1.setPosition(getSize().x/2f, getSize().y/2f);
        label1.getPosition().sub(label1.getPreferredSize().x/2f, label1.getPreferredSize().y/2f);

        TruiLabel label2 = addChild(new TruiLabel("Test label 2", getContext().getFont("Consolas", 12)));
        label2.setPosition(label1.getPosition());
        label2.getPosition().sub(0, label1.getPreferredSize().y+10f);
        label2.getPosition().x = getSize().x/2f - label2.getPreferredSize().x/2f;
    }
}
