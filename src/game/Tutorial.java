package game;

import javax.swing.*;
import java.awt.*;

public class Tutorial {

    JFrame gameFrame = new JFrame();
    JLabel text = new JLabel("This is the tutorial");

    public void Guide(){

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.add(text);

        gameFrame.setLayout(new FlowLayout()); //Default layout
        gameFrame.setSize(1000,1000);
        gameFrame.setVisible(true);

    }
}