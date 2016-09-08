package org.jglrxavpok.trui.render;

public class PolygonalShape implements Shape {

    private final VertexInfo[] vertices;
    private final float[] packedPositions;

    public PolygonalShape(VertexInfo[] vertices) {
        this.vertices = vertices;
        packedPositions = new float[vertices.length*2];
        for (int i = 0; i < vertices.length; i++) {
            packedPositions[i*2] = vertices[i].getPosition().x;
            packedPositions[i*2 +1] = vertices[i].getPosition().y;
        }
    }

    @Override
    public VertexInfo[] getVertices() {
        return vertices;
    }

    @Override
    public float[] getPackedVerticePositions() {
        return packedPositions;
    }
}
