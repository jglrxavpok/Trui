import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.libgdx.LibGDXBackend;
import org.jglrxavpok.trui.testui.TestScreen;

public class DesktopTestApplication implements ApplicationListener {
    private TruiContext context;

    @Override
    public void create() {
        context = new TruiContext(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        context.setBackend(new LibGDXBackend()); // TODO: find another way to set the backend ?

        context.setCurrentScreen(new TestScreen());
        Gdx.input.setInputProcessor(new TestInputProcessor(context));
    }

    @Override
    public void resize(int width, int height) {
        context.setWidth(width);
        context.setHeight(height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        context.renderAll();
    }

    @Override
    public void pause() {
        context.loseFocus();
    }

    @Override
    public void resume() {
        context.gainFocus();
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
