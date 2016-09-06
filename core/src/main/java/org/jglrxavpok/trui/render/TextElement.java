package org.jglrxavpok.trui.render;

import org.jglrxavpok.trui.backends.TruiFont;

public class TextElement implements RenderElement {
    private final String text;
    private final TruiFont font;
    private final int xOffset;
    private final int yOffset;

    public TextElement(String text, TruiFont font, int xOffset, int yOffset) {
        this.text = text;
        this.font = font;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public TruiFont getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
