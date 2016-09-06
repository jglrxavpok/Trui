package org.jglrxavpok.trui.render;

import org.joml.Vector2f;

public class NoShape implements Shape {

    private static final Vector2f[] EMPTY_ARRAY = {};

    @Override
    public Vector2f[] getVertices() {
        return EMPTY_ARRAY;
    }
}
