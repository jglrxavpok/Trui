import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.libgdx.LibGDXBackend;
import org.jglrxavpok.trui.testui.TestScreen;

public class DesktopTestApplication implements ApplicationListener {
    private TruiContext context;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        context = new TruiContext(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();
        context.setBackend(new LibGDXBackend(spriteBatch)); // TODO: find another way to set the backend ?

        context.setCurrentScreen(new TestScreen());
        Gdx.input.setInputProcessor(new TestInputProcessor(context));

        camera = new OrthographicCamera();
    }

    @Override
    public void resize(int width, int height) {
        context.setWidth(width);
        context.setHeight(height);
        Gdx.gl.glViewport(0,0,width, height);
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();
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
