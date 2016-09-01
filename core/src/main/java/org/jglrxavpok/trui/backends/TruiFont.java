package org.jglrxavpok.trui.backends;

public interface TruiFont {

    String getName();

    int getSize();

    float getTextWidth(String text);

    float getTextHeight(String text);
}
