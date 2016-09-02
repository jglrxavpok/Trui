package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.layouts.PreferredFitLayout;
import org.jglrxavpok.trui.utils.TruiColor;

public class TruiButton extends TruiPanel {

    public TruiButton() {
        super();
        setMinSize(10,10);
        setLayout(new PreferredFitLayout(this));
    }

    public TruiButton(String text, TruiFont font, TruiColor textColor) {
        this(new TruiLabel(text, font).setTextColor(textColor));
    }

    public TruiButton(TruiComponent content) {
        this();
        addChild(content);
        pack();
    }
}
