package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.DelaunayTriangulator;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import com.google.common.collect.Maps;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.UIRenderer;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFont;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.render.*;
import org.jglrxavpok.trui.utils.TruiColor;
import org.joml.Vector2f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibGDXUIRenderer implements UIRenderer {
    private final TruiContext context;
    private final PolygonSpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Texture testTexture;
    private final DelaunayTriangulator triangulator;
    private final Texture singlePixelTexture;
    private final Texture missingTexture;
    private final Map<RenderInfo, PolygonRegion> cachedPolygons;
    private Affine2 transform;

    public LibGDXUIRenderer(TruiContext context, PolygonSpriteBatch spriteBatch, OrthographicCamera camera) {
        transform = new Affine2();
        this.context = context;
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        triangulator = new DelaunayTriangulator();

        testTexture = new Texture("test_pic.png");
        singlePixelTexture = new Texture("blank.png");
        missingTexture = singlePixelTexture;

        cachedPolygons = Maps.newHashMap();
    }

    @Override
    public void renderComponent(TruiComponent component) {
        float height = Gdx.graphics.getHeight(); // TODO: better handle y inversion
        List<RenderInfo> list = component.getRenderProperties().getRenderInfos();
        for(RenderInfo r : list) {
            RenderElementType type = r.getShapeType();
            switch (type) {
                case TEXT: {
                    TextElement textElement = (TextElement) r.getRenderElement();
                    LibGDXFont font = (LibGDXFont) textElement.getFont();
                    BitmapFont bitmapFont = font.getBitmapFont();
                    TruiColor color = ((ColorPaintStyle) r.getPaintStyle()).getColor();
                    bitmapFont.setColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                    bitmapFont.draw(spriteBatch, textElement.getText(), component.getPosition().x, height - component.getPosition().y - 1f);
                }
                break;

                case SHAPE_FILLED: {
                    TruiColor color = TruiColor.TMP;
                    if(r.getPaintStyle() instanceof ColorPaintStyle) {
                        color.set(((ColorPaintStyle) r.getPaintStyle()).getColor());
                    } else {
                        color.setRed(1f);
                        color.setGreen(1f);
                        color.setBlue(1f);
                        color.setAlpha(1f);
                    }
                    if(!cachedPolygons.containsKey(r)) {
                        cachedPolygons.put(r, buildPolygonRegion(r, height));
                    }
                    spriteBatch.setColor(color.getARGBInt());
                    spriteBatch.draw(cachedPolygons.get(r),0,0);
                }
                break;

                case SHAPE_LINES: {
                    Shape shape = (Shape) r.getRenderElement();
                    Texture texture = getCorrespondingTexture(r.getPaintStyle());
                    TextureRegion region = new TextureRegion(texture);
                    VertexInfo[] vertices = shape.getVertices();
                    final float lineWidth = 1f; // TODO: Add method to choose width
                    TruiColor lineColor = TruiColor.TMP;
                    if(r.getPaintStyle() instanceof ColorPaintStyle) {
                        lineColor.set(((ColorPaintStyle) r.getPaintStyle()).getColor());
                    } else {
                        lineColor.setRed(1f);
                        lineColor.setGreen(1f);
                        lineColor.setBlue(1f);
                        lineColor.setAlpha(1f);
                    }
                    for (int i = 0; i < vertices.length; i++) {
                        VertexInfo current = vertices[i];
                        int nextIndex = (i+1) % vertices.length;
                        VertexInfo next = vertices[nextIndex];

                        drawLine(region, current, next, lineWidth, lineColor, height);
                    }
                }
                break;
            }
        }
    }

    private PolygonRegion buildPolygonRegion(RenderInfo r, float height) {
        Shape shape = (Shape) r.getRenderElement();
        Texture texture = getCorrespondingTexture(r.getPaintStyle());
        float[] packedVertices = new float[shape.getPackedVerticePositions().length];
        for (int i = 0; i < packedVertices.length; i++) {
            packedVertices[i] = shape.getPackedVerticePositions()[i];
        }
        for (int i = 1; i < packedVertices.length; i+=2) {
            packedVertices[i] = height-shape.getPackedVerticePositions()[i];
        }
        ShortArray triangleIndices = triangulator.computeTriangles(packedVertices, false);
        short[] indices = triangleIndices.toArray();
        PolygonRegion pregion = new PolygonRegion(new TextureRegion(texture), packedVertices, indices);
        return pregion;
    }

    private void drawLine(TextureRegion region, VertexInfo a, VertexInfo b, float lineWidth, TruiColor lineColor, float height) {
        float deltaX = b.getPosition().x - a.getPosition().x;
        float deltaY = b.getPosition().y - a.getPosition().y;
        float distance = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        float angle = (float) Math.atan2(deltaY, deltaX);
        transform.idt();
        transform.setToTranslation(a.getPosition().x, height-a.getPosition().y);
        transform.rotateRad(-angle);

        // the trick is to render a rotated rectangle
        spriteBatch.setColor(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha());
        spriteBatch.draw(region, distance, lineWidth, transform);
        spriteBatch.setColor(1,1,1,1);
    }

    private Texture getCorrespondingTexture(PaintStyle style) {
        if(style instanceof ColorPaintStyle) {
            return singlePixelTexture;
        } else if(style instanceof TexturePaintStyle) {
            return testTexture; // TODO
        }
        return missingTexture;
    }

    @Override
    public void endRendering() {
        spriteBatch.end();
    }

    @Override
    public void startRendering() {
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
    }
}
