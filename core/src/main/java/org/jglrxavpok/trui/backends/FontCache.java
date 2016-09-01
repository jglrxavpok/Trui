package org.jglrxavpok.trui.backends;

import com.google.common.collect.Maps;

import java.util.Map;

public class FontCache {

    private final Map<String, Map<Integer, TruiFont>> nameMap;

    public FontCache() {
        nameMap = Maps.newHashMap();
    }

    public void cache(TruiFont font) {
        if(!nameMap.containsKey(font.getName())) {
            Map<Integer, TruiFont> sizeMap = Maps.newHashMap();
            nameMap.put(font.getName(), sizeMap);
        }

        if(!nameMap.get(font.getName()).containsKey(font.getSize())) {
            nameMap.get(font.getName()).put(font.getSize(), font);
        }
    }

    public boolean hasCached(String name, int size) {
        if(!nameMap.containsKey(name))
            return false;
        return nameMap.get(name).containsKey(size);
    }

    public TruiFont getCached(String name, int size) {
        if(hasCached(name, size))
            return nameMap.get(name).get(size);
        return null;
    }
}
