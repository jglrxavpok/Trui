package org.jglrxavpok.trui;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import org.jglrxavpok.trui.backends.*;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.components.TruiScreen;
import org.jglrxavpok.trui.events.FocusGainedEvent;
import org.jglrxavpok.trui.events.FocusLostEvent;
import org.jglrxavpok.trui.events.UIEvent;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents the current state of the Trui components. Also helps to dispatch UI events such as mouse clicks, mouse movements...
 */
public class TruiContext {

    private final TruiScreen defaultScreen;
    private final EventBus eventBus;
    private final List<TruiComponent> registred;
    private final FontCache fontCache;
    private float width;
    private float height;
    private TruiScreen currentScreen;
    private TruiBackend backend;
    private ComponentRenderer componentRenderer;
    private TruiFontFactory fontFactory;
    private TruiComponent focused;
    private Map<String, InputStream> registredFontStreams;

    public TruiContext(float contextWidth, float contextHeight) {
        registredFontStreams = Maps.newHashMap();
        this.width = contextWidth;
        this.height = contextHeight;
        eventBus = new EventBus();
        registred = new LinkedList<TruiComponent>();
        defaultScreen = new TruiScreen();
        setCurrentScreen(defaultScreen);
        fontCache = new FontCache();
    }

    public void setBackend(TruiBackend backend) {
        this.backend = backend;
        componentRenderer = backend.createComponentRenderer(this);
        fontFactory = backend.createFontFactory(this, fontCache);
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
        resizeCurrentScreen(width, height);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        resizeCurrentScreen(width, height);
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        resizeCurrentScreen(width, height);
    }

    private void resizeCurrentScreen(float width, float height) {
        if(getCurrentScreen() != null) {
            if(backend != null) {
                backend.resize(width, height);
            }
            getCurrentScreen().setSize(width, height);
            getCurrentScreen().invalidateHierarchy();
        }
    }

    public TruiFont getFont(String name, int size) {
        if(fontCache.hasCached(name, size))
            return fontCache.getCached(name, size);
        if(fontFactory == null)
            throw new NullPointerException("No font factory registred with context. Probably that no backend has been set");
        TruiFont font = fontFactory.create(name, registredFontStreams.get(name), size);
        fontCache.cache(font);
        return font;
    }

    /**
     * Renders the current screen.
     * Be aware of your backend when using the method because some graphical library only allow one thread to use the library (ie OpenGL)
     */
    public void renderAll() {
        if(componentRenderer == null)
            throw new NullPointerException("No component renderer registred with context. Probably that no backend has been set");
        componentRenderer.startRendering();
        renderPanel(getCurrentScreen());
        componentRenderer.endRendering();
    }

    private void renderPanel(TruiPanel panel) {
        componentRenderer.renderComponent(panel); // render behind everything
        for(TruiComponent c : panel.getChildren()) {
            if(c instanceof TruiPanel) {
                renderPanel((TruiPanel) c);
            } else {
                componentRenderer.renderComponent(c);
            }
        }
    }

    public void loseFocus() {
        fireEvent(new FocusLostEvent());
    }

    public void gainFocus() {
        fireEvent(new FocusGainedEvent());
    }

    public void focusOn(TruiComponent component) {
        focused = component;
    }

    public void unfocusFromAll() {
        focused = null;
    }

    public TruiComponent getFocusedComponent() {
        return focused;
    }

    public void registerFontStream(String name, InputStream stream) {
        registredFontStreams.put(name, stream);
    }
}
