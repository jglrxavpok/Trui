package org.jglrxavpok.trui.render;

import org.joml.Vector2f;

public class PolygonalShape implements Shape {

    private final Vector2f[] vertices;

    public PolygonalShape(Vector2f[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public Vector2f[] getVertices() {
        return vertices;
    }
}
