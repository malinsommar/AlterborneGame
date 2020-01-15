package davidtest.overworld.map.Functionality;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * Class to simulate a RightClick in the game
 */
public class MouseClickSimulated {
    /**
     * call robot to do my bidding
     */
    public static Robot robot = null;

    /**
     * simulate the act of pressing the MouseButton
     */
    public MouseClickSimulated() {
        {
            try {
                //assign the robot a name
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //click within the space of the screen
            click(650, 320); //assign x and y-axis within the screen that the mouse will click
        }
    }

    /**
     * Set the x and y axel
     * @param x position on the screen
     * @param y position on the screen
     */
    public static void click(int x, int y) {
        robot.mouseMove(x, y); //parameter names
        robot.delay(1); //delay between activation of method and the input
        robot.mousePress(MouseEvent.BUTTON1_MASK); //simulate a button-press
        robot.mouseRelease(MouseEvent.BUTTON1_MASK); // simulate a button-release
    }
}