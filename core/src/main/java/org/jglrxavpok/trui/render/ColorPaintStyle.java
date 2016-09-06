package org.jglrxavpok.trui.render;

import org.jglrxavpok.trui.utils.TruiColor;

public class ColorPaintStyle extends PaintStyle {

    private final TruiColor color;

    public ColorPaintStyle(TruiColor color) {
        this.color = color;
    }

    public TruiColor getColor() {
        return color;
    }
}
