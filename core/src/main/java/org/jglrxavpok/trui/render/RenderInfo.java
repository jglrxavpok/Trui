package org.jglrxavpok.trui.render;

public class RenderInfo {
    private final RenderElement renderElement;
    private final RenderElementType shapeType;
    private final PaintStyle paintStyle;

    public RenderInfo(RenderElement renderElement, RenderElementType shapeType, PaintStyle paintStyle) {
        this.renderElement = renderElement;
        this.shapeType = shapeType;
        this.paintStyle = paintStyle;
    }

    public RenderElement getRenderElement() {
        return renderElement;
    }

    public RenderElementType getShapeType() {
        return shapeType;
    }

    public PaintStyle getPaintStyle() {
        return paintStyle;
    }
}
