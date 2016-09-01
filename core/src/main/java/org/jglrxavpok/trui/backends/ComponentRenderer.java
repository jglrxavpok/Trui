package org.jglrxavpok.trui.backends;

import org.jglrxavpok.trui.components.TruiComponent;

public interface ComponentRenderer {

    void renderComponent(TruiComponent component);

    void endRendering();

    void startRendering();
}
