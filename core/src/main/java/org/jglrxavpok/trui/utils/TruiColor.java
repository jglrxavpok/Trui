package org.jglrxavpok.trui.utils;

public class TruiColor {

    private float red;
    private float green;
    private float blue;
    private float alpha;

    public TruiColor(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getARGBInt() {
        return getAlphaInt() << 24 | getRedInt() << 16 | getGreenInt() << 8 | getBlueInt();
    }

    public int getRedInt() {
        return (int) (red * 255) & 0xFF;
    }

    public int getBlueInt() {
        return (int) (blue * 255) & 0xFF;
    }

    public int getGreenInt() {
        return (int) (green * 255) & 0xFF;
    }

    public int getAlphaInt() {
        return (int) (alpha * 255) & 0xFF;
    }

    public static TruiColor opaqueBlack() {
        return new TruiColor(0,0,0,1);
    }

    public static TruiColor opaqueWhite() {
        return new TruiColor(1,1,1,1);
    }
}
