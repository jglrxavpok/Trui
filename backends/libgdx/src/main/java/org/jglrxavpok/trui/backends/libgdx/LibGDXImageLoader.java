package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.graphics.Texture;
import org.jglrxavpok.trui.backends.ImageLoader;
import org.jglrxavpok.trui.render.TruiImage;
import org.jglrxavpok.trui.utils.TruiResource;

public class LibGDXImageLoader implements ImageLoader {
    @Override
    public TruiImage load(final TruiResource from) {
        final Texture texture = new Texture(from.getID()); // TODO: Maybe change from using internal path
        return new TruiImage() {
            @Override
            public int getWidth() {
                return texture.getWidth();
            }

            @Override
            public int getHeight() {
                return texture.getHeight();
            }

            @Override
            public byte[] getRawData() {
                return from.getRawData();
            }

            @Override
            public String getID() {
                return from.getID();
            }
        };
    }
}
