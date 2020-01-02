package davidtest.overworld.map.Functionality;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseClickSimulated {
    public static Robot robot = null;

    public MouseClickSimulated() {
        {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            click(650, 320);
        }
    }

    public static void click(int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(1);
        robot.mousePress(MouseEvent.BUTTON1_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
    }
}