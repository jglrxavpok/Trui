package org.jglrxavpok.trui.testui;

import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.components.TruiScreen;
import org.jglrxavpok.trui.layouts.PreferredFitLayout;
import org.jglrxavpok.trui.utils.TruiColor;

public class TestScreen extends TruiScreen {

    @Override
    public void show() {
        clear();
        setLayout(new PreferredFitLayout(this));

        TruiFont textFont = getContext().getFont("Consolas", 28);
        TruiLabel label1 = addChild(new TruiLabel("Test label", textFont));
        TruiLabel label2 = addChild(new TruiLabel("Test label 2", textFont));

        TruiButton button1 = addChild(new TruiButton("Test Button", textFont, TruiColor.opaqueBlack()));

        pack();
    }
}
