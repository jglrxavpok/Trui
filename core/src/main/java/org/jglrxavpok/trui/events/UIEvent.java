package org.jglrxavpok.trui.events;

/**
 * Represents a single UI event such as mouse clicks, mouse movements, key presses/releases, etc.
 */
public abstract class UIEvent {

    private boolean cancelled;

    public UIEvent() {

    }

    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * If this event supports being cancelled (see {@link #isCancellable()}), cancels it. Otherwise will throw an {@link UnsupportedOperationException}
     */
    public void cancel() {
        if(isCancellable()) {
            cancelled = true;
        } else {
            throw new UnsupportedOperationException("Event "+getClass().getCanonicalName()+" cannot be cancelled");
        }
    }

    protected abstract boolean isCancellable();
}
