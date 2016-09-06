package org.jglrxavpok.trui.render;

public class TexturePaintStyle extends PaintStyle {

    private final TruiImage image;

    public TexturePaintStyle(TruiImage image) {
        this.image = image;
    }

    public TruiImage getImage() {
        return image;
    }
}
