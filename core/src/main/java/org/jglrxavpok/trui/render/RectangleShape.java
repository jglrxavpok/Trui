package org.jglrxavpok.trui.render;

import org.jglrxavpok.trui.utils.TruiColor;
import org.joml.Vector2f;

public class RectangleShape extends PolygonalShape {
    public RectangleShape(Vector2f minXY, Vector2f maxXY) {
        this(minXY, maxXY, new Vector2f(), new Vector2f(1,1));
    }

    public RectangleShape(Vector2f minXY, Vector2f maxXY, Vector2f minUV, Vector2f maxUV) {
        super(new VertexInfo[]{
                new VertexInfo(new Vector2f(minXY.x, minXY.y), new Vector2f(minUV.x, maxUV.y), TruiColor.opaqueWhite()),
                new VertexInfo(new Vector2f(maxXY.x, minXY.y), new Vector2f(maxUV.x, maxUV.y), TruiColor.opaqueWhite()),
                new VertexInfo(new Vector2f(maxXY.x, maxXY.y), new Vector2f(maxUV.x, minUV.y), TruiColor.opaqueWhite()),
                new VertexInfo(new Vector2f(minXY.x, maxXY.y), new Vector2f(minUV.x, minUV.y), TruiColor.opaqueWhite())
        });
    }
}
