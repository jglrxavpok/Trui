package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.utils.ImageTranslator;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.jglrxavpok.trui.render.TruiImage;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

public class NanoVGImageTranslator extends ImageTranslator<Integer> {

    private final LWJGLBackend backend;

    public NanoVGImageTranslator(LWJGLBackend backend) {
        this.backend = backend;
    }

    private NanoVGContext nvgContext() {
        return backend.getNVGContext();
    }

    @Override
    protected Integer create(TruiImage image) {
        ByteBuffer data = ByteBuffer.allocateDirect(image.getRawData().length).put(image.getRawData());
        data.flip();
        int flags = 0;
        int handle = NanoVG.nvgCreateImageMem(nvgContext().getHandle(), flags, data);
        if(handle == 0) {
            System.err.println("Could not load: "+image.getID()+" ("+image.getWidth()+"x"+image.getHeight()+")");
        }
        return handle;
    }
}
