package org.jglrxavpok.trui.backends.libgdx.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import org.jglrxavpok.trui.backends.TruiFont;

public final class LibGDXFont implements TruiFont {

    private final GlyphLayout glyphLayout;
    private BitmapFont font;
    private final String name;
    private final int size;

    LibGDXFont(BitmapFont font, String name, int size, GlyphLayout glyphLayout) {
        this.font = font;
        this.name = name;
        this.size = size;
        this.glyphLayout = glyphLayout;
    }

    public BitmapFont getBitmapFont() {
        return font;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public float getTextWidth(String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    @Override
    public float getTextHeight(String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }
}
