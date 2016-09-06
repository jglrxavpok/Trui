package org.jglrxavpok.trui.render;

public class TextElement implements RenderElement {
    private final String text;
    private final int xOffset;
    private final int yOffset;

    public TextElement(String text, int xOffset, int yOffset) {
        this.text = text;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
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
