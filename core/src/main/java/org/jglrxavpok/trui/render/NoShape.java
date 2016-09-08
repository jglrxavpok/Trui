package org.jglrxavpok.trui.render;

public class NoShape implements Shape {

    private static final VertexInfo[] EMPTY_ARRAY = {};

    @Override
    public VertexInfo[] getVertices() {
        return EMPTY_ARRAY;
    }

    @Override
    public float[] getPackedVerticePositions() {
        return new float[0];
    }
}
