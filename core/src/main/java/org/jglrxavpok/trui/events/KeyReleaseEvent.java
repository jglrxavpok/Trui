package org.jglrxavpok.trui.events;

public class KeyReleaseEvent extends KeyEvent {

    public KeyReleaseEvent(int keycode) {
        super(keycode);
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
