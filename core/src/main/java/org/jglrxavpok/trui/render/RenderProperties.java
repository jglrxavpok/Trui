package org.jglrxavpok.trui.render;

import com.google.common.collect.Lists;
import org.jglrxavpok.trui.components.TruiComponent;

import java.util.List;

public class RenderProperties {
    private final TruiComponent owner;
    private final List<RenderInfo> renderInfos;

    public RenderProperties(final TruiComponent owner) {
        this.owner = owner;
        renderInfos = Lists.newArrayList();
    }

    public TruiComponent getOwner() {
        return owner;
    }

    public void addRenderElement(String text, RenderElementType shapeType, PaintStyle paintStyle) {
        addRenderElement(text, 0, 0, shapeType, paintStyle);
    }

    public void addRenderElement(String text, int xOffset, int yOffset, RenderElementType shapeType, PaintStyle paintStyle) {
        addRenderElement(new TextElement(text, xOffset, yOffset), shapeType, paintStyle);
    }

    public void addRenderElement(RenderElement renderElement, RenderElementType shapeType, PaintStyle paintStyle) {
        renderInfos.add(new RenderInfo(renderElement, shapeType, paintStyle));
    }

    public List<RenderInfo> getRenderInfos() {
        return renderInfos;
    }
}
