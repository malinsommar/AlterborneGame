package davidtest.overworld.map.Functionality;

import java.awt.*;
import java.awt.event.MouseEvent;

//Class to simulate a RightClick in the game
public class MouseClickSimulated {
    public static Robot robot = null; //call robot to do my bidding

    public MouseClickSimulated() {
        {
            try {
                //assign the robot a name
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //click within the space of the screen
            click(650, 320);//set the x and y-axis within the screen
        }
    }
    public static void click(int x, int y) {
        robot.mouseMove(x, y); //assign parameter
        robot.delay(1); //delay between activation of method and the input
    }
}