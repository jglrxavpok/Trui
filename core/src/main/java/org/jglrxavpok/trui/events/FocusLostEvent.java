package org.jglrxavpok.trui.events;

public class FocusLostEvent extends UIEvent {
    @Override
    protected boolean isCancellable() {
        return true;
    }
}
