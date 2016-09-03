package org.jglrxavpok.trui.backends;

import org.jglrxavpok.trui.TruiContext;

public interface TruiBackend {

    String getName();

    ComponentRenderer createComponentRenderer(TruiContext context);

    TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache);

    void resize(float width, float height);
}
