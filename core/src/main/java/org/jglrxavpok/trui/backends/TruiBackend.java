package org.jglrxavpok.trui.backends;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.utils.ImageTranslator;

public interface TruiBackend {

    String getName();

    UIRenderer createComponentRenderer(TruiContext context);

    TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache);

    void resize(float width, float height);

    ResourceLoader createResourceLoader(TruiContext context);

    ImageLoader createImageLoader(TruiContext context);
}
