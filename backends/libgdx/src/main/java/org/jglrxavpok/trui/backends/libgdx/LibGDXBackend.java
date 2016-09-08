package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.UIRenderer;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiBackend;
import org.jglrxavpok.trui.backends.TruiFontFactory;
import org.jglrxavpok.trui.backends.libgdx.fonts.LibGDXFontFactory;

public class LibGDXBackend implements TruiBackend {

    private final PolygonSpriteBatch spriteBatch;
    private final OrthographicCamera camera;

    public LibGDXBackend() {
        this.spriteBatch = new PolygonSpriteBatch();
        camera = new OrthographicCamera();
    }

    @Override
    public String getName() {
        return "LibGDX";
    }

    @Override
    public UIRenderer createComponentRenderer(TruiContext context) {
        return new LibGDXUIRenderer(context, spriteBatch, camera);
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new LibGDXFontFactory(context);
    }

    @Override
    public void resize(float width, float height) {
        camera.setToOrtho(false, width, height);
    }

}
