package org.jglrxavpok.trui.render;

import org.jglrxavpok.trui.utils.TruiColor;
import org.joml.Vector2f;

public class VertexInfo {

    private final Vector2f position;
    private final Vector2f uvCoordinate;
    private final TruiColor color;

    public VertexInfo(Vector2f position, Vector2f uvCoordinate, TruiColor color) {
        this.position = position;
        this.uvCoordinate = uvCoordinate;
        this.color = color;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getUVCoordinate() {
        return uvCoordinate;
    }

    public TruiColor getColor() {
        return color;
    }
}
