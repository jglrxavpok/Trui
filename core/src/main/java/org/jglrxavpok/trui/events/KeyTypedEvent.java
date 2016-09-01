package org.jglrxavpok.trui.events;

public class KeyTypedEvent extends UIEvent {

    private final char character;

    public KeyTypedEvent(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
