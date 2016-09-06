package org.jglrxavpok.trui.render;

import org.joml.Vector2f;

public class RectangleShape extends PolygonalShape {
    public RectangleShape(Vector2f minXY, Vector2f maxXY) {
        super(new Vector2f[] {
            new Vector2f(minXY.x, minXY.y),
            new Vector2f(maxXY.x, minXY.y),
            new Vector2f(maxXY.x, maxXY.y),
            new Vector2f(minXY.x, maxXY.y)
        });
    }
}
