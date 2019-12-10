package game;

import fight.ForestFight;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RouteScreen extends JFrame {

    //TODO byta ut mot davids overview

    Font pixelMplus;
    JButton forrestChoice, caveChoice,shopChoice;
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
        add(shopChoice);

        forrestChoice.addActionListener(e-> dispose());
       // forrestChoice.addActionListener(e-> new ForestFight());
        shopChoice.addActionListener(e-> dispose());
        shopChoice.addActionListener(e-> new Shop());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setVisible(true);
    }

    public void allButtons(){

        //Forest
        forrestChoice = new JButton(forest);
        forrestChoice.setSize(300, 300);
        forrestChoice.setLocation(100, 100);

        shopChoice = new JButton("shop");
        shopChoice.setSize(300, 300);
        shopChoice.setLocation(900, 100);

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