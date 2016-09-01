package org.jglrxavpok.trui.events;

public class ScrollEvent extends UIEvent {
    private final int amount;

    public ScrollEvent(int amount) {
        this.amount = amount;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }

    public int getAmount() {
        return amount;
    }
}
