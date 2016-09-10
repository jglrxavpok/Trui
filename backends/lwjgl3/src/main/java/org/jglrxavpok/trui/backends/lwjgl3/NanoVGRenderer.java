package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.UIRenderer;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.render.*;
import org.jglrxavpok.trui.utils.TruiColor;
import org.lwjgl.nanovg.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class NanoVGRenderer implements UIRenderer {
    private final TruiContext context;
    private final LWJGLBackend backend;
    private final NVGColor nvgColor;
    private final NVGPaint nvgPaint;
    private final NanoVGImageTranslator nvgImageHandler;

    public NanoVGRenderer(TruiContext context, LWJGLBackend backend) {
        this.context = context;
        this.backend = backend;
        nvgColor = NVGColor.calloc();
        nvgPaint = NVGPaint.calloc();
        nvgImageHandler = new NanoVGImageTranslator(backend);
    }

    @Override
    public void renderComponent(TruiComponent component) {
        MemoryStack memStack = MemoryStack.stackPush();
        for(RenderInfo r : component.getRenderProperties().getRenderInfos()) {
            switch (r.getShapeType()) {
                case TEXT: {
                    TextElement textElement = (TextElement) r.getRenderElement();
                    TruiFont font = textElement.getFont();
                    String text = textElement.getText();
                    TruiColor truiColor = ((ColorPaintStyle) r.getPaintStyle()).getColor();
                    nvgColor.a(truiColor.getAlpha());
                    nvgColor.r(truiColor.getRed());
                    nvgColor.g(truiColor.getGreen());
                    nvgColor.b(truiColor.getBlue());
                    NanoVG.nvgFillColor(nvgContext().getHandle(), nvgColor);
                    nvgContext().setFontSize(font.getSize());
                    nvgContext().setFont(font);
                    NanoVG.nvgText(nvgContext().getHandle(), component.getPosition().x+textElement.getXOffset()-5f,component.getPosition().y+font.getTextHeight(text)*.75f+textElement.getYOffset(), text, MemoryUtil.NULL);
                }
                break;

                case SHAPE_FILLED:
                case SHAPE_LINES: {

                    Shape shape = (Shape) r.getRenderElement();
                    VertexInfo[] vertices = shape.getVertices();

                    if(vertices.length > 0) {
                        TruiColor color = TruiColor.TMP;
                        nvgPaint.image(0); // reset NVGPaint image
                        if(r.getPaintStyle() instanceof ColorPaintStyle) {
                            TruiColor paintColor = ((ColorPaintStyle) r.getPaintStyle()).getColor();
                            color.set(paintColor);
                        } else {
                            if(r.getPaintStyle() instanceof TexturePaintStyle) {
                                TruiImage image = ((TexturePaintStyle) r.getPaintStyle()).getImage();
                                int imageID = nvgImageHandler.get(image);
                                NanoVG.nvgImagePattern(nvgContext().getHandle(), component.getPosition().x,component.getPosition().y, component.getSize().x, component.getSize().y, 0, imageID, 1, nvgPaint);
                            }
                            color.setAlpha(1f);
                            color.setBlue(1f);
                            color.setGreen(1f);
                            color.setRed(1f);
                        }
                        nvgColor.a(color.getAlpha());
                        nvgColor.r(color.getRed());
                        nvgColor.g(color.getGreen());
                        nvgColor.b(color.getBlue());
                        nvgPaint.innerColor(nvgColor);
                        nvgPaint.outerColor(nvgColor);

                        NanoVG.nvgBeginPath(nvgContext().getHandle());
                        NanoVG.nvgFillPaint(nvgContext().getHandle(), nvgPaint);
                        NanoVG.nvgStrokePaint(nvgContext().getHandle(), nvgPaint);
                    //    NanoVG.nvgFillColor(nvgContext().getHandle(), nvgColor);
                    //    NanoVG.nvgStrokeColor(nvgContext().getHandle(), nvgColor);
                        NanoVG.nvgMoveTo(nvgContext().getHandle(), vertices[0].getPosition().x, vertices[0].getPosition().y);
                        for (int i = 1;i<vertices.length+1;i++) {
                            VertexInfo v = vertices[i % vertices.length];
                            NanoVG.nvgLineTo(nvgContext().getHandle(), v.getPosition().x, v.getPosition().y);
                        }

                        if(r.getShapeType() == RenderElementType.SHAPE_FILLED) {
                            NanoVG.nvgFill(nvgContext().getHandle());
                        } else {
                            NanoVG.nvgStroke(nvgContext().getHandle());
                        }
                    }
                }
                break;
            }
        }
        memStack.pop();
    }

    @Override
    public void endRendering() {
        nvgContext().endFrame();
    }

    @Override
    public void startRendering() {
        nvgContext().beginFrame((int)(context.getWidth()), (int)(context.getHeight()), 1f);
    }

    private NanoVGContext nvgContext() {
        return backend.getNVGContext();
    }
}
