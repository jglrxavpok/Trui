import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.jglrxavpok.trui.TruiContext;

public class DesktopTestApplication implements ApplicationListener {
    private TruiContext context;

    @Override
    public void create() {
        context = new TruiContext(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        context.setWidth(width);
        context.setHeight(height);
    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 768;
        config.resizable = true;
        config.title = "Trui libgdx backend test application";
        new LwjglApplication(new DesktopTestApplication(), config);
    }
}
