package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.utils.TruiColor;

public class TruiLabel extends TruiComponent {

    private String text;
    private TruiColor textColor;
    private TruiFont font;

    public TruiLabel(String text, TruiFont font) {
        super();
        setFont(font);
        setText(text);
        textColor = new TruiColor(0,0,0,1f); // opaque black
    }

    public String getText() {
        return text;
    }

    public TruiLabel setText(String text) {
        this.text = text;
        resize();
        return this;
    }

    public TruiColor getTextColor() {
        return textColor;
    }

    public TruiLabel setTextColor(TruiColor textColor) {
        this.textColor = textColor;
        return this;
    }

    public TruiFont getFont() {
        return font;
    }

    public TruiLabel setFont(TruiFont font) {
        this.font = font;
        resize();
        return this;
    }

    private void resize() {
        if(text != null && font != null) {
            setPreferredSize(font.getTextWidth(text), font.getTextHeight(text));
            setMinSize(getPreferredSize());
            setMaxSize(getPreferredSize());
            invalidateHierarchy();
        }
    }

}
