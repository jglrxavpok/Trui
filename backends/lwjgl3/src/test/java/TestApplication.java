import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class TestApplication {

    public static void main(String[] args) {
        glfwInit();

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        long window = glfwCreateWindow(1024, 768, "Test application", 0, 0);
        glfwShowWindow(window);

        while(!glfwWindowShouldClose(window)) {

            glfwPollEvents();
        }
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
