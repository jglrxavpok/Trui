package org.jglrxavpok.trui.style;

import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiImageComponent;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.render.*;
import org.jglrxavpok.trui.utils.TruiColor;
import org.joml.Vector2f;

public class DefaultStyle extends TruiStyle {
    @Override
    public void decorate(TruiComponent component) {
        RenderProperties render = component.getRenderProperties();
        Vector2f min = new Vector2f(component.getPosition());
        Vector2f max = new Vector2f(component.getPosition()).add(component.getSize());
        if(component instanceof TruiLabel) {
            TruiLabel label = ((TruiLabel) component);
            render.addRenderElement(label.getText(), label.getFont(), RenderElementType.TEXT, new ColorPaintStyle(label.getTextColor()));
        } else if(component instanceof TruiButton) {
            render.addRenderElement(new RectangleShape(min, max),
                    RenderElementType.SHAPE_FILLED,
                    new ColorPaintStyle(TruiColor.opaqueLightGray())); // content

            render.addRenderElement(new RectangleShape(min, max),
                    RenderElementType.SHAPE_LINES,
                    new ColorPaintStyle(TruiColor.opaqueBlack())); // border
        } else if (component instanceof TruiImageComponent) {
            render.addRenderElement(new RectangleShape(min, max),
                    RenderElementType.SHAPE_FILLED,
                    new TexturePaintStyle(((TruiImageComponent) component).getImage()));
        }

    }
}
