package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoseScreen {

    JFrame loseFrame = new JFrame();

    JLabel died, forces, kingdom, who;
    JButton continueButton;
    private Font pixelMplus;
    TextField name;

    public void loseScreen() {

        loseFrame.setLayout(null);
        loseFrame.setSize(1920, 1080);
        loseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        importFont();
        importLabels();

        loseFrame.add(died);
        loseFrame.add(forces);
        loseFrame.add(kingdom);
        loseFrame.add(who);
        loseFrame.add(name);
        loseFrame.add(continueButton);

        //loseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //loseFrame.setUndecorated(true);
        loseFrame.setVisible(true);
    }

    private void importLabels(){
        died = new JLabel("You Died");
        died.setForeground(Color.black);
        died.setFont(pixelMplus.deriveFont(150f));
        Dimension diedSize = died.getPreferredSize();
        died.setBounds(350, 60, diedSize.width, diedSize.height);

        forces = new JLabel("You challenged forces too great for you. Geru'xelm is doomed.");
        forces.setForeground(Color.black);
        forces.setFont(pixelMplus.deriveFont(30f));
        Dimension forcesSize = forces.getPreferredSize();
        forces.setBounds(200, 250, forcesSize.width, forcesSize.height);

        kingdom = new JLabel("You will be remembered as a hero, if the human race survives.");
        kingdom.setForeground(Color.black);
        kingdom.setFont(pixelMplus.deriveFont(30f));
        Dimension kingdomSize = kingdom.getPreferredSize();
        kingdom.setBounds(200, 300, kingdomSize.width, kingdomSize.height);

        continueButton = new JButton("Fade away");
        continueButton.setSize(300, 100);
        continueButton.setLocation(480, 600);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(35f));
        continueButton.setBackground(Color.darkGray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
    }


    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}