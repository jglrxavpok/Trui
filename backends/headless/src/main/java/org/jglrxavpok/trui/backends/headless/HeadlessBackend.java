package org.jglrxavpok.trui.backends.headless;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.*;

import java.io.InputStream;

public class HeadlessBackend implements TruiBackend {
    @Override
    public String getName() {
        return "Headless";
    }

    @Override
    public UIRenderer createComponentRenderer(TruiContext context) {
        return new HeadlessUIRenderer();
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new TruiFontFactory() {
            @Override
            public TruiFont create(final String name, InputStream input, final int size) {
                return new TruiFont() {
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
                        return 0;
                    }

                    @Override
                    public float getTextHeight(String text) {
                        return 0;
                    }
                };
            }
        };
    }

    @Override
    public void resize(float width, float height) {
        // no op
    }
}
