package org.jglrxavpok.trui.events;

public class MouseMoveEvent extends UIEvent {
    private final float screenX;
    private final float screenY;
    private final float deltaX;
    private final float deltaY;

    public MouseMoveEvent(float screenX, float screenY, float deltaX, float deltaY) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public float getScreenX() {
        return screenX;
    }

    public float getScreenY() {
        return screenY;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
