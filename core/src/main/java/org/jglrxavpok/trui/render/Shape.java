package org.jglrxavpok.trui.render;

import org.jglrxavpok.trui.components.TruiComponent;
import org.joml.Vector2f;

public interface Shape extends RenderElement {

    VertexInfo[] getVertices();

    float[] getPackedVerticePositions();
}
