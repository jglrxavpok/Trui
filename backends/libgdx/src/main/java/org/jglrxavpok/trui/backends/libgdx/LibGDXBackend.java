package org.jglrxavpok.trui.backends.libgdx;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiBackend;
import org.jglrxavpok.trui.backends.TruiFontFactory;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFontFactory;

public class LibGDXBackend implements TruiBackend {
    @Override
    public String getName() {
        return "LibGDX";
    }

    @Override
    public ComponentRenderer createComponentRenderer(TruiContext context) {
        return new LibGDXComponentRenderer(context);
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new LibGDXFontFactory(context, fontCache);
    }

}
