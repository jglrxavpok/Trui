package org.jglrxavpok.trui.events;

public class PointerUpEvent extends PointerButtonEvent {

    public PointerUpEvent(int screenX, int screenY, int pointer, int button) {
        super(screenX, screenY, pointer, button);
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
