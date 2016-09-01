import org.jglrxavpok.trui.components.TruiScreen;
import org.jglrxavpok.trui.components.TruiComponent;
import org.jglrxavpok.trui.components.TruiPanel;
import org.jglrxavpok.trui.layouts.AbsoluteLayout;
import org.joml.Vector2f;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestsLayout {

    @Test
    public void testPackAbsolute() {
        TruiScreen screen = new TruiScreen();
        TruiComponent a = new TruiComponent();
        a.setPreferredSize(100, 100).setPosition(100, 100);

        TruiComponent b = new TruiComponent();
        b.setPreferredSize(1867, 133).setPosition(-415, -9);

        TruiPanel testPanel = new TruiPanel();
        screen.addChild(testPanel);
        testPanel.setLayout(new AbsoluteLayout(testPanel));
        testPanel.addChild(a).addChild(b);
        testPanel.pack();


        assertEquals(new Vector2f(1867, 100+100+9), testPanel.getSize());
    }
}
