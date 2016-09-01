package org.jglrxavpok.trui.events;

public class PointerDownEvent extends PointerButtonEvent {

    public PointerDownEvent(int screenX, int screenY, int pointer, int button) {
        super(screenX, screenY, pointer, button);
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
