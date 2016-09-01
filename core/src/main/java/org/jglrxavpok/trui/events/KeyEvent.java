package org.jglrxavpok.trui.events;

public class KeyEvent extends UIEvent {

    private final int keycode;

    public KeyEvent(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
