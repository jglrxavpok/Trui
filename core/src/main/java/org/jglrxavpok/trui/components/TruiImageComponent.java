package org.jglrxavpok.trui.components;

import org.jglrxavpok.trui.render.TruiImage;

public class TruiImageComponent extends TruiComponent {

    private TruiImage image;
    private float scaleX;
    private float scaleY;

    public TruiImageComponent(TruiImage image) {
        this(image, 1f);
    }

    public TruiImageComponent(TruiImage image, float scale) {
        this(image, scale, scale);
    }

    public TruiImageComponent(TruiImage image, float scaleX, float scaleY) {
        super();
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setImage(image);
    }

    public TruiImageComponent setImage(TruiImage image) {
        this.image = image;
        setPreferredSize(image.getWidth()*scaleX, image.getHeight()*scaleY);
        return this;
    }

    public TruiImageComponent setScale(float scale) {
        return setScaleX(scale).setScaleY(scale);
    }

    public float getScaleX() {
        return scaleX;
    }

    public TruiImageComponent setScaleX(float scaleX) {
        this.scaleX = scaleX;
        return this;
    }

    public float getScaleY() {
        return scaleY;
    }

    public TruiImageComponent setScaleY(float scaleY) {
        this.scaleY = scaleY;
        return this;
    }

    public TruiImage getImage() {
        return image;
    }
}
