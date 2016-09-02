package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiBackend;
import org.jglrxavpok.trui.backends.TruiFontFactory;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFont;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFontFactory;

public class LibGDXBackend implements TruiBackend {

    private final SpriteBatch spriteBatch;

    public LibGDXBackend(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public String getName() {
        return "LibGDX";
    }

    @Override
    public ComponentRenderer createComponentRenderer(TruiContext context) {
        return new LibGDXComponentRenderer(context, spriteBatch);
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new LibGDXFontFactory(context, fontCache);
    }

}
