package org.jglrxavpok.trui.style;

import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.render.*;
import org.jglrxavpok.trui.utils.TruiColor;
import org.joml.Vector2f;

public class DefaultStyle extends TruiStyle {
    @Override
    public void decorate(TruiComponent component) {
        RenderProperties render = component.getRenderProperties();
        if(component instanceof TruiLabel) {
            render.addRenderElement(((TruiLabel) component).getText(), RenderElementType.TEXT, PaintStyle.NONE);
        } else if(component instanceof TruiButton) {
            render.addRenderElement(new RectangleShape(component.getPosition(), new Vector2f(component.getPosition()).add(component.getSize())),
                    RenderElementType.SHAPE_FILLED,
                    new ColorPaintStyle(TruiColor.opaqueLightGray())); // content

            render.addRenderElement(new RectangleShape(component.getPosition(), new Vector2f(component.getPosition()).add(component.getSize())),
                    RenderElementType.SHAPE_LINES,
                    new ColorPaintStyle(TruiColor.opaqueBlack())); // border
        }
    }
}
