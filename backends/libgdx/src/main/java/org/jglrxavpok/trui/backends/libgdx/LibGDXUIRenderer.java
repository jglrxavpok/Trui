package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.UIRenderer;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFont;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.render.ColorPaintStyle;
import org.jglrxavpok.trui.render.RenderElementType;
import org.jglrxavpok.trui.render.RenderInfo;
import org.jglrxavpok.trui.render.TextElement;
import org.jglrxavpok.trui.utils.TruiColor;

import java.util.List;

public class LibGDXUIRenderer implements UIRenderer {
    private final TruiContext context;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Texture testTexture;

    public LibGDXUIRenderer(TruiContext context, SpriteBatch spriteBatch, OrthographicCamera camera) {
        this.context = context;
        this.spriteBatch = spriteBatch;
        this.camera = camera;

        testTexture = new Texture("test_pic.png");
    }

    @Override
    public void renderComponent(TruiComponent component) {
        // TODO: find better suited way to handle component types
        float height = Gdx.graphics.getHeight(); // TODO: better handle y inversion
        /*if(component instanceof TruiLabel) {
            TruiLabel label = ((TruiLabel) component);
            LibGDXFont font = (LibGDXFont)label.getFont();
            BitmapFont bitmapFont = font.getBitmapFont();
            bitmapFont.setColor(label.getTextColor().getRed(), label.getTextColor().getGreen(), label.getTextColor().getBlue(), label.getTextColor().getAlpha());
            bitmapFont.draw(spriteBatch, label.getText(), label.getPosition().x, height-label.getPosition().y-1f);
        } else if(component instanceof TruiButton) {
            TruiButton button = ((TruiButton) component);
            // TODO: change to user-defined textures
            spriteBatch.draw(testTexture, button.getPosition().x, height-button.getPosition().y-1f-button.getSize().y, button.getSize().x, button.getSize().y);
        }*/
        List<RenderInfo> list = component.getRenderProperties().getRenderInfos();
        for(RenderInfo r : list) {
            RenderElementType type = r.getShapeType();
            switch (type) {
                case TEXT:
                    TextElement textElement = (TextElement) r.getRenderElement();
                    LibGDXFont font = (LibGDXFont) textElement.getFont();
                    BitmapFont bitmapFont = font.getBitmapFont();
                    TruiColor color = ((ColorPaintStyle)r.getPaintStyle()).getColor();
                    bitmapFont.setColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                    bitmapFont.draw(spriteBatch, textElement.getText(), component.getPosition().x, height-component.getPosition().y-1f);
                    break;
            }
        }
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
