package org.jglrxavpok.trui.backends.libgdx.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.files.FileHandleStream;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.TruiFontFactory;

import java.io.InputStream;

public class LibGDXFontFactory implements TruiFontFactory {
    private final TruiContext context;
    private final GlyphLayout glyphLayout;

    public LibGDXFontFactory(TruiContext context) {
        this.context = context;
        glyphLayout = new GlyphLayout();
    }

    @Override
    public TruiFont create(String name, final InputStream input, int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = size;
        FileHandle file = Gdx.files.internal(name+".ttf");
        BitmapFont bitmapFont;
        if(file.exists()) {
            bitmapFont = new FreeTypeFontGenerator(file).generateFont(params);
        } else {
            bitmapFont = new FreeTypeFontGenerator(new FileHandle() {
                @Override
                public InputStream read() {
                    return input;
                }
            }).generateFont(params);
        }
        return new LibGDXFont(bitmapFont, name, size, glyphLayout);
    }
}
