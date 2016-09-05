package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.TruiFontFactory;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.system.MemoryStack;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class NanoVGFontFactory implements TruiFontFactory {

    private final LWJGLBackend backend;

    public NanoVGFontFactory(TruiContext context, LWJGLBackend backend) {
        this.backend = backend;
    }

    @Override
    public TruiFont create(String name, InputStream input, int size) {
        NanoVGContext context = backend.getNVGContext();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        byte[] buffer = new byte[2*1024];
        try {
            while((i = input.read(buffer)) != -1) {
                baos.write(buffer, 0, i);
            }
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        ByteBuffer data = ByteBuffer.allocateDirect(bytes.length);
        for (int j = 0; j < bytes.length; j++) {
            data.put(bytes[j]);
        }
        data.flip();
        NanoVG.nvgCreateFontMem(context.getHandle(), name, data, 0);
        return new NanoVGFont(name, size, data, backend.getNVGContext());
    }
}
