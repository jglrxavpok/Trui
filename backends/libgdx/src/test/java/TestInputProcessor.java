import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.events.*;

public class TestInputProcessor implements InputProcessor {
    private final TruiContext context;
    private Vector2 oldPosition;

    public TestInputProcessor(TruiContext context) {
        oldPosition = new Vector2();
        this.context = context;
    }

    @Override
    public boolean keyDown(int keycode) {
        return context.fireEvent(new KeyPressEvent(keycode));
    }

    @Override
    public boolean keyUp(int keycode) {
        return context.fireEvent(new KeyReleaseEvent(keycode));
    }

    @Override
    public boolean keyTyped(char character) {
        return context.fireEvent(new KeyTypedEvent(character));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return context.fireEvent(new PointerDownEvent(screenX, screenY, pointer, button));
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return context.fireEvent(new PointerUpEvent(screenX, screenY, pointer, button));
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return context.fireEvent(new PointerDraggedEvent(screenX, screenY, pointer));
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        boolean processed = context.fireEvent(new MouseMoveEvent(screenX, screenY, screenX-oldPosition.x, screenY-oldPosition.y));
        oldPosition.set(screenX, screenY);
        return processed;
    }

    @Override
    public boolean scrolled(int amount) {
        return context.fireEvent(new ScrollEvent(amount));
    }
}
