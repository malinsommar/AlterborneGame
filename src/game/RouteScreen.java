package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RouteScreen extends JFrame {

    //TODO byta ut mot davids overview

    Font pixelMplus;
    JButton forrestChoice, caveChoice;
    ImageIcon forest = new ImageIcon("forest.jpg");
    ImageIcon cave = new ImageIcon("cave.png");

    public RouteScreen(){

        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1920, 1080);

        importFont();
        allButtons();

        add(forrestChoice);
        add(caveChoice);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setVisible(true);
    }

    public void allButtons(){

        //Forest
        forrestChoice = new JButton(forest);
        forrestChoice.setSize(300, 300);
        forrestChoice.setLocation(100, 100);

        caveChoice = new JButton(cave);
        caveChoice.setSize(300, 300);
        caveChoice.setLocation(500, 100);
    }

    public void importFont() {

        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
            //textenduvillläggain.setFont(pixelMplus.deriveFont(30f));

        } catch (IOException | FontFormatException e) {
        }
    }
}