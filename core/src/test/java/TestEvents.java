import com.google.common.eventbus.Subscribe;
import org.jglrxavpok.trui.TruiContext;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.events.UIEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEvents {

    @Test
    public void testEventDispatch() {
        TruiContext context = new TruiContext(0,0);

        TruiComponent receiving = new TruiComponent() {

            @Subscribe
            public void onEventReceived(UIEvent any) {
                any.cancel();
            }
        };

        TruiComponent notReceiving = new TruiComponent() {

            @Subscribe
            public void onEventReceived(UIEvent any) {
                throw new IllegalStateException("This component must not receive the event");
            }
        };

        context.getCurrentScreen().addChild(receiving);


        boolean cancelled = context.fireEvent(new UIEvent() {
            @Override
            protected boolean isCancellable() {
                return true;
            }
        });
        assertEquals(true, cancelled);
    }
}
