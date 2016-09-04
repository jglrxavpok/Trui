package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiBackend;
import org.jglrxavpok.trui.backends.TruiFontFactory;

public class LWJGLBackend implements TruiBackend {
    @Override
    public String getName() {
        return "LWJGL3";
    }

    @Override
    public ComponentRenderer createComponentRenderer(TruiContext context) {
        return null;
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return null;
    }

    @Override
    public void resize(float width, float height) {

    }
}
