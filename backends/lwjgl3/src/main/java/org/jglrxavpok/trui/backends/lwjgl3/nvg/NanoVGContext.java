package org.jglrxavpok.trui.backends.lwjgl3.nvg;

import org.jglrxavpok.trui.backends.TruiFont;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.system.MemoryUtil;

public class NanoVGContext {

    private final long handle;
    private TruiFont currentFont;
    private int currentFontSize;

    public NanoVGContext(long handle) {
        this.handle = handle;
    }

    public float[] getTextBounds(String text, TruiFont font) {
        setFontSize(font.getSize());
        setFont(font);
        float[] bounds = new float[4];
        NanoVG.nvgTextBounds(handle, 0,0,text, MemoryUtil.NULL,bounds);
        return bounds;
    }

    public void setFont(TruiFont font) {
        currentFont = font;
        NanoVG.nvgFontFace(handle, font.getName());
    }

    public TruiFont getFont() {
        return currentFont;
    }

    public void setFontSize(int size) {
        currentFontSize = size;
        NanoVG.nvgFontSize(handle, size);
    }

    public int getFontSize() {
        return currentFontSize;
    }

    public long getHandle() {
        return handle;
    }

    public void endFrame() {
        NanoVG.nvgEndFrame(handle);
    }

    public void beginFrame(int width, int height, float pixelRatio) {
        NanoVG.nvgBeginFrame(handle, width, height, pixelRatio);
    }
}
