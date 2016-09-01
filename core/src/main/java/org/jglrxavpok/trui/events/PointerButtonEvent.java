package org.jglrxavpok.trui.events;

public abstract class PointerButtonEvent extends PointerEvent {
    private final int button;

    public PointerButtonEvent(int screenX, int screenY, int pointer, int button) {
        super(screenX, screenY, pointer);
        this.button = button;
    }

    public int getButton() {
        return button;
    }
}
