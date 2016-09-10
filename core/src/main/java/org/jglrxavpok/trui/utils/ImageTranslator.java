package org.jglrxavpok.trui.utils;

import com.google.common.collect.Maps;
import org.jglrxavpok.trui.render.TruiImage;

import java.util.HashMap;
import java.util.Map;

public abstract class ImageTranslator<Target> {

    private final Map<TruiImage, Target> map;

    public ImageTranslator() {
        map = Maps.newHashMap();
    }

    public Target get(TruiImage image) {
        if(!map.containsKey(image))
            map.put(image, create(image));
        return map.get(image);
    }

    protected abstract Target create(TruiImage image);
}
