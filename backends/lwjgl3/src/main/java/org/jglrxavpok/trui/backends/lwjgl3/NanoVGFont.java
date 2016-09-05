package org.jglrxavpok.trui.backends.lwjgl3;

import org.jglrxavpok.trui.backends.TruiFont;
import org.jglrxavpok.trui.backends.lwjgl3.nvg.NanoVGContext;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;

import java.nio.ByteBuffer;

public class NanoVGFont implements TruiFont {
    private final String name;
    private final int size;
    private final ByteBuffer data;
    private final NanoVGContext context;

    public NanoVGFont(String name, int size, ByteBuffer data, NanoVGContext context) {
        this.name = name;
        this.size = size;
        this.data = data;
        this.context = context;
    }

    // DISCLAIMER: VERY IMPORTANT, PREVENTS THE GC TO DESTROY THIS DATA. NANOVG REQUIRES THAT IT IS ALWAYS PRESENT IN MEMORY IN ORDER TO WORK
    public ByteBuffer getData() {
        return data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public float getTextWidth(String text) {
        float[] bounds = context.getTextBounds(text, this);
        System.out.println("bounds: "+bounds[0]+","+bounds[1]+","+bounds[2]+","+bounds[3]);
        return bounds[2]-bounds[0];
    }

    @Override
    public float getTextHeight(String text) {
        float[] bounds = context.getTextBounds(text, this);
        return bounds[3]-bounds[1];
    }
}
