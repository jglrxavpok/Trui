package org.jglrxavpok.trui.backends.headless;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.*;

public class HeadlessBackend implements TruiBackend {
    @Override
    public String getName() {
        return "Headless";
    }

    @Override
    public ComponentRenderer createComponentRenderer(TruiContext context) {
        return new HeadlessComponentRenderer();
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new TruiFontFactory() { // TODO: Caching
            @Override
            public TruiFont getFont(final String name, final int size) {
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
}
