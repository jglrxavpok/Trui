package org.jglrxavpok.trui.backends;

import org.jglrxavpok.trui.components.TruiComponent;

public interface UIRenderer {

    void renderComponent(TruiComponent component);

    void endRendering();

    void startRendering();
}
