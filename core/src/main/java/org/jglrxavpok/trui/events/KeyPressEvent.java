package org.jglrxavpok.trui.events;

public class KeyPressEvent extends KeyEvent {

    public KeyPressEvent(int keycode) {
        super(keycode);
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
