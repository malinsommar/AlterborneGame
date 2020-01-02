package davidtest.overworld.map;

import davidtest.overworld.levels.Level;
import davidtest.overworld.entities.Player;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.gfx.SpriteSheet;
import game.FightModel;
import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class OverWorldFrame extends Canvas {
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
    InputHandler input;


    public JFrame frame;

     public OverWorldFrame() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));


        frame = new JFrame(NAME);
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);//Center Canvas within the JFrame
        frame.pack(); //Sets frames above or at preferred size
         input = new InputHandler(this); //call input-object

         //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setResizable(false);//Not resizable
        frame.setLocationRelativeTo(null);//center the frame
        frame.setVisible(true); //show frame
    }
}

