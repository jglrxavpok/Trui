package org.jglrxavpok.trui.events;

public abstract class PointerEvent extends UIEvent {

    private final int screenX;
    private final int screenY;
    private final int pointer;

    public PointerEvent(int screenX, int screenY, int pointer) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getPointer() {
        return pointer;
    }
}
