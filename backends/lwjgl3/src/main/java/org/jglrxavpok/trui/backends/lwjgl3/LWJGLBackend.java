package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.ComponentRenderer;
import org.jglrxavpok.trui.backends.FontCache;
import org.jglrxavpok.trui.backends.TruiBackend;
import org.jglrxavpok.trui.backends.TruiFontFactory;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.nanovg.NanoVGGL3;
import org.lwjgl.nanovg.NanoVGGLES2;
import org.lwjgl.nanovg.NanoVGGLES3;
import sun.font.Font2D;
import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.font.PhysicalFont;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;

public class LWJGLBackend implements TruiBackend {


    private final OpenGLVersion glVersion;
    private NanoVGContext nvgContext;

    public LWJGLBackend(OpenGLVersion glVersion) {
        this.glVersion = glVersion;
    }

    private File getFontFile(String name) throws ReflectiveOperationException {
        FontManager manager = FontManagerFactory.getInstance();
        Font f = new Font(name, 0, 10);

        Font2D f2d = manager.findFont2D(f.getFontName(), f.getStyle(),
                FontManager.LOGICAL_FALLBACK).handle.font2D;

        Field platName = PhysicalFont.class.getDeclaredField("platName");
        platName.setAccessible(true);
        String fontPath = (String)platName.get(f2d);
        platName.setAccessible(false);

        return new File(fontPath);
    }

    @Override
    public String getName() {
        return "LWJGL3";
    }

    @Override
    public ComponentRenderer createComponentRenderer(TruiContext context) {
        return new NanoVGRenderer(context, this);
    }

    @Override
    public TruiFontFactory createFontFactory(TruiContext context, FontCache fontCache) {
        return new NanoVGFontFactory(context, this);
    }

    @Override
    public void resize(float width, float height) {

    }

    public NanoVGContext getNVGContext() {
        if(nvgContext == null) {
            // lazy initialization of the NanoVG context in case the renderer is created at a time the OpenGL context is not available
            int flags = NanoVGGL2.NVG_ANTIALIAS | NanoVGGL2.NVG_DEBUG;
            switch (glVersion) {
                case GL2:
                    nvgContext = new NanoVGContext(NanoVGGL2.nvgCreateGL2(flags));
                    break;

                case GL3:
                    nvgContext = new NanoVGContext(NanoVGGL3.nvgCreateGL3(flags));
                    break;

                case GLES2:
                    nvgContext = new NanoVGContext(NanoVGGLES2.nvgCreateGLES2(flags));
                    break;

                case GLES3:
                    nvgContext = new NanoVGContext(NanoVGGLES3.nvgCreateGLES3(flags));
                    break;
            }
        }
        return nvgContext;
    }
}
