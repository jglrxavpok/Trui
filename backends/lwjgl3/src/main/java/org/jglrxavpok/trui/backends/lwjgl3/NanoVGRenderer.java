package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.UIRenderer;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.utils.TruiColor;
import org.lwjgl.nanovg.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class NanoVGRenderer implements UIRenderer {
    private final TruiContext context;
    private final LWJGLBackend backend;
    private final NVGColor nvgColor;

    public NanoVGRenderer(TruiContext context, LWJGLBackend backend) {
        this.context = context;
        this.backend = backend;
        nvgColor = NVGColor.calloc();
    }

    @Override
    public void renderComponent(TruiComponent component) {
        MemoryStack memStack = MemoryStack.stackPush();
        if(component instanceof TruiButton) { // TODO: distinguish in a better way
            nvgColor.a(1f);
            nvgColor.r(1f);
            nvgColor.g(0f);
            nvgColor.b(0f);
            NanoVG.nvgBeginPath(nvgContext().getHandle());
            NanoVG.nvgFillColor(nvgContext().getHandle(), nvgColor);
            NanoVG.nvgRect(nvgContext().getHandle(), component.getPosition().x,component.getPosition().y,component.getSize().x,component.getSize().y);
            NanoVG.nvgFill(nvgContext().getHandle());
        } else if(component instanceof TruiLabel) {
            TruiLabel label = ((TruiLabel) component);
            TruiColor truiColor = label.getTextColor();
            nvgColor.a(truiColor.getAlpha());
            nvgColor.r(truiColor.getRed());
            nvgColor.g(truiColor.getGreen());
            nvgColor.b(truiColor.getBlue());
            NanoVG.nvgFillColor(nvgContext().getHandle(), nvgColor);
            nvgContext().setFontSize(label.getFont().getSize());
            nvgContext().setFont(label.getFont());
            NanoVG.nvgText(nvgContext().getHandle(), label.getPosition().x-5f,label.getPosition().y+label.getFont().getTextHeight(label.getText())*.75f, label.getText(), MemoryUtil.NULL);
        }

        memStack.pop();
    }

    @Override
    public void endRendering() {
        nvgContext().endFrame();
    }

    @Override
    public void startRendering() {
        nvgContext().beginFrame((int)(context.getWidth()), (int)(context.getHeight()), context.getWidth()/context.getHeight());
    }

    private NanoVGContext nvgContext() {
        return backend.getNVGContext();
    }
}
