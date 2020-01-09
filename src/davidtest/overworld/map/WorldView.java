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

public class WorldView extends Canvas {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "OverWorld";
    public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public int[] colours = new int[6 * 6 * 6];
    public Screen screen;
    public Level level1;
    public Player player;


    public JFrame frame;

     public WorldView() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));


        frame = new JFrame(NAME);
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);//Center Canvas within the JFrame
        frame.pack(); //Sets frames above or at preferred size
         InputHandler input = new InputHandler(this); //call input-object
         screen = new Screen(WorldView.WIDTH, WorldView.HEIGHT, new SpriteSheet("/resources/spriteSheet/Sprite_sheet.png"));//call spriteSheet-object within the frames height and width
         level1 = new Level("/resources/maps/Map.png");//call map-object
         player = new Player(level1, 230, 235, input); //call Player-object and position it on map. Assign the input class to player

         level1.addEntity(player); //add player onto the screen


         //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setResizable(false);//Not resizable
        frame.setLocationRelativeTo(null);//center the frame
        frame.setVisible(true); //show frame
    }
}

