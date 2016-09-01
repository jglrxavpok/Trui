package org.jglrxavpok.trui.events;

public class PointerDraggedEvent extends PointerEvent {
    public PointerDraggedEvent(int screenX, int screenY, int pointer) {
        super(screenX, screenY, pointer);
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
