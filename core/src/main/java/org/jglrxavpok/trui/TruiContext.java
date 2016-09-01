package org.jglrxavpok.trui;

import com.google.common.eventbus.EventBus;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.components.TruiScreen;
import org.jglrxavpok.trui.events.UIEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the current state of the Trui components. Also helps to dispatch UI events such as mouse clicks, mouse movements...
 */
public class TruiContext {

    private final TruiScreen defaultScreen;
    private final EventBus eventBus;
    private final List<TruiComponent> registred;
    private float width;
    private float height;
    private TruiScreen currentScreen;

    public TruiContext(float contextWidth, float contextHeight) {
        this.width = contextWidth;
        this.height = contextHeight;
        eventBus = new EventBus();
        registred = new LinkedList<TruiComponent>();
        defaultScreen = new TruiScreen();
        setCurrentScreen(defaultScreen);
    }

    public TruiScreen getDefaultScreen() {
        return defaultScreen;
    }

    public TruiScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(TruiScreen newScreen) {
        newScreen.setContext(this);
        if(newScreen != currentScreen) {
            if(currentScreen != null) {
                currentScreen.hide();
                unregisterEvents(currentScreen);
            }
            newScreen.setPreferredSize(width, height);
            newScreen.setMinSize(width, height);
            newScreen.setMaxSize(width, height);
            newScreen.setSize(width, height);
            newScreen.show();
            registerEvents(newScreen);
        }
        this.currentScreen = newScreen;
    }

    /**
     * Dispatches the event across the components and returns true if the event has been cancelled.
     * @param event
     *          The event to dispatch
     * @return
     *          <code>true</code> if the event has been cancelled
     */
    public boolean fireEvent(UIEvent event) {
        eventBus.post(event);
        return event.isCancelled();
    }

    private void unregisterEvents(TruiPanel panel) {
        if(isRegistred(panel))
            eventBus.unregister(panel);
        for(TruiComponent c : panel.getChildren()) {
            if(c instanceof TruiPanel) {
                unregisterEvents((TruiPanel) c);
            } else {
                if(isRegistred(c))
                    eventBus.unregister(c);
                registred.remove(c);
            }
        }
    }

    private boolean isRegistred(TruiComponent c) {
        return registred.contains(c);
    }

    private void registerEvents(TruiPanel panel) {
        if(!isRegistred(panel))
            eventBus.register(panel);
        registred.add(panel);
        for(TruiComponent c : panel.getChildren()) {
            if(c instanceof TruiPanel) {
                registerEvents((TruiPanel) c);
            } else {
                if(!isRegistred(c))
                    eventBus.register(c);
                registred.add(c);
            }
        }
    }

    public void notifyHierarchyChange(TruiComponent caller) {
        if(caller == currentScreen) {
            unregisterEvents(currentScreen);
            registerEvents(currentScreen);
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
