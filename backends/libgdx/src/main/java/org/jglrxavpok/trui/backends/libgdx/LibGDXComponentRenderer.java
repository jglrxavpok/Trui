package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFont;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;

public class LibGDXComponentRenderer implements ComponentRenderer {
    private final TruiContext context;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Texture testTexture;

    public LibGDXComponentRenderer(TruiContext context, SpriteBatch spriteBatch, OrthographicCamera camera) {
        this.context = context;
        this.spriteBatch = spriteBatch;
        this.camera = camera;

        testTexture = new Texture("test_pic.png");
    }

    @Override
    public void renderComponent(TruiComponent component) {
        // TODO: find better suited way to handle component types
        float height = Gdx.graphics.getHeight(); // TODO: better handle y inversion
        if(component instanceof TruiLabel) {
            TruiLabel label = ((TruiLabel) component);
            LibGDXFont font = (LibGDXFont)label.getFont();
            BitmapFont bitmapFont = font.getBitmapFont();
            bitmapFont.setColor(label.getTextColor().getRed(), label.getTextColor().getGreen(), label.getTextColor().getBlue(), label.getTextColor().getAlpha());
            bitmapFont.draw(spriteBatch, label.getText(), label.getPosition().x, height-label.getPosition().y-1f);
        } else if(component instanceof TruiButton) {
            TruiButton button = ((TruiButton) component);
            // TODO: change to user-defined textures
            spriteBatch.draw(testTexture, button.getPosition().x, height-button.getPosition().y-1f-button.getSize().y, button.getSize().x, button.getSize().y);
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
