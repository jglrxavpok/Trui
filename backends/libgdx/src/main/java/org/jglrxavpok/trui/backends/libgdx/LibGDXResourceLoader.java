package org.jglrxavpok.trui.backends.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.jglrxavpok.trui.backends.ResourceLoader;
import org.jglrxavpok.trui.utils.TruiResource;

public class LibGDXResourceLoader implements ResourceLoader {
    @Override
    public TruiResource load(final String id) {
        final FileHandle handle = Gdx.files.internal(id);
        return new TruiResource() {
            @Override
            public byte[] getRawData() {
                return handle.readBytes();
            }

            @Override
            public String getID() {
                return id;
            }
        };
    }
}
