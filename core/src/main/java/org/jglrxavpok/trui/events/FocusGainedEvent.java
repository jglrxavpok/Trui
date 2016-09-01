package org.jglrxavpok.trui.events;

public class FocusGainedEvent extends UIEvent {
    @Override
    protected boolean isCancellable() {
        return true;
    }
}
