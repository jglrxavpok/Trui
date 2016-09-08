import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.backends.lwjgl3.LWJGLBackend;
import org.jglrxavpok.trui.backends.lwjgl3.OpenGLVersion;
import org.jglrxavpok.trui.components.TruiButton;
import org.jglrxavpok.trui.components.TruiLabel;
import org.jglrxavpok.trui.events.MouseMoveEvent;
import org.jglrxavpok.trui.testui.TestScreen;
import org.jglrxavpok.trui.utils.TruiColor;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class LWJGL3TestApplication {

    private static TruiContext context;

    public static void main(String[] args) {
        glfwInit();

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        int width = 1024;
        int height = 768;
        long window = glfwCreateWindow(width, height, "Trui LWJGL3/NanoVG backend test application", 0, 0);
        glfwShowWindow(window);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        init(window, width, height);

        glViewport(0,0,width,height);
        while(!glfwWindowShouldClose(window)) {
            render();
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private static void init(long window, float w, float h) {
        context = new TruiContext(w, h);
        context.registerFontStream("Consolas", LWJGL3TestApplication.class.getResourceAsStream("/Consolas.ttf"));
        context.setBackend(new LWJGLBackend(OpenGLVersion.GL3));
        context.setCurrentScreen(new TestScreen());

        glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {

            private double prevX;
            private double prevY;

            @Override
            public void invoke(long window, double xpos, double ypos) {
                context.fireEvent(new MouseMoveEvent((float)(xpos), (float)(ypos), (float)(prevX-xpos), (float)(prevY-ypos)));
                prevX = xpos;
                prevY = ypos;
            }
        });

        glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(action == GLFW_RELEASE) {
                    if(key == GLFW_KEY_R) {
                        context.getCurrentScreen().addChild(new TruiButton("TEST", context.getFont("Consolas", 28), TruiColor.opaqueBlack()));
                        context.getCurrentScreen().pack();
                    }
                }
            }
        });

        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {

            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0,0,width,height);
                context.setSize(width, height);
            }
        });
    }

    private static void render() {
        glClearColor(1,1,1,1);
        glClear(GL_COLOR_BUFFER_BIT);
        context.renderAll();
    }
}
