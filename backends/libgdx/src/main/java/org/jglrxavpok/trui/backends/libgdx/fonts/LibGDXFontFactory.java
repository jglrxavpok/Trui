package org.jglrxavpok.trui.backends.libgdx.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.TruiFontFactory;

public class LibGDXFontFactory implements TruiFontFactory {
    private final TruiContext context;
    private final FontCache fontCache;
    private final GlyphLayout glyphLayout;

    public LibGDXFontFactory(TruiContext context, FontCache fontCache) {
        this.context = context;
        this.fontCache = fontCache;
        glyphLayout = new GlyphLayout();
    }

    @Override
    public TruiFont getFont(String name, int size) {
        if(fontCache.hasCached(name, size))
            return fontCache.getCached(name, size);
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = size;
        BitmapFont bitmapFont = new FreeTypeFontGenerator(Gdx.files.internal(name+".ttf")).generateFont(params);
        LibGDXFont font = new LibGDXFont(bitmapFont, name, size, glyphLayout);
        fontCache.cache(font);
        return font;
    }
}
