package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFont;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiLabel;

public class LibGDXComponentRenderer implements ComponentRenderer {
    private final TruiContext context;
    private final SpriteBatch spriteBatch;

    public LibGDXComponentRenderer(TruiContext context) {
        this.context = context;
        spriteBatch = new SpriteBatch();
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
        }
    }

    @Override
    public void endRendering() {
        spriteBatch.end();
    }

    @Override
    public void startRendering() {
        spriteBatch.begin();
    }
}
