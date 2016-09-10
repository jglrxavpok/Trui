package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.backends.ResourceLoader;
import org.jglrxavpok.trui.utils.IOHelper;
import org.jglrxavpok.trui.utils.TruiResource;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathResourceLoader implements ResourceLoader {
    @Override
    public TruiResource load(String id) {
        InputStream input = getClass().getResourceAsStream("/"+id);
        byte[] data = new byte[0];
        try {
            data = IOHelper.readAll(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final byte[] actualData = data;
        return new TruiResource() {
            @Override
            public byte[] getRawData() {
                return actualData;
            }

            @Override
            public String getID() {
                return id;
            }
        };
    }
}
