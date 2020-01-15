package davidtest.overworld.map;

import davidtest.overworld.gfx.SpriteSheet;
import davidtest.overworld.levels.Level;
import davidtest.overworld.entities.Player;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.map.Functionality.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * WorldView with the variables necessary to show a frame on the screen
 */
public class WorldView extends Canvas {
    static final int WIDTH = 260; //The games width. Can be viewed as a sort of camera-view that moves further away when the number increases
    private static final int HEIGHT = WIDTH / 12 * 9; //Control the height of game. Same as with Width
    private static final int SCALE = 7; //Control the size of the tiles
    private static final String NAME = "AlterBorne"; //header

    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //Overlay the frame with the image
    int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //assign data to the pixel variable

    int[] colours = new int[6 * 6 * 6]; //call the colour-class
    public Screen screen; //call the screen-class
    Level level; //call the level-class
    public Player player; //call the player-class


    public JFrame frame; //Call the JFrame

    /**
     * start the WorldView
     * @param userName the value of players name
     */
     public void startWorldView(String userName) {
         //Control the frames area. In this case it is based on what the already assigned Height and Width says
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));


        frame = new JFrame(NAME); //create JFrame object
        frame.setUndecorated(true); //hide borders
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit the game when pressing the exit button
        frame.setLayout(new BorderLayout()); //assign canvas to frame
        frame.add(this, BorderLayout.CENTER);//Center Canvas/the game within the JFrame
        frame.pack(); //Sets the size of the content so its either the above or preferred size
         InputHandler input = new InputHandler(this); //call input-object
         screen = new Screen(WorldView.WIDTH, WorldView.HEIGHT, new SpriteSheet("/resources/spriteSheet/Sprite_sheet.png"));//call spriteSheet-object within the frames height and width
         level = new Level("/resources/maps/Map.png");//call map-object
         player = new Player(level, 230, 235, input, userName); //call Player-object and position it on map. Assign the input class to player
         level.addEntity(player); //add player onto the screen
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setResizable(false);//Not resizable
        frame.setLocationRelativeTo(null);//center the frame on the screen
        frame.setVisible(true); //show frame
    }
}

