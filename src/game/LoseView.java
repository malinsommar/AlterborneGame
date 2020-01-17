package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * LoseView contains all swing variables to start LoseFrame.
 *
 * @author Malin Sommar
 */
public class LoseView {

    private JFrame loseFrame = new JFrame();

    private JLabel died, top1, top2;
    JButton continueButton;
    private Font pixelMplus;
    private int finalLevel;

    /**
     *Starts the lose JFrame and calls private methods needed for the frame.
     */
    public void loseScreen(int level) {

        finalLevel = level;

        loseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loseFrame.setLayout(null);
        loseFrame.setSize(500, 400);
        loseFrame.setLocation(400,138);

        importFont();
        importLabels();

        loseFrame.add(died);
        loseFrame.add(top1);
        loseFrame.add(top2);
        loseFrame.add(continueButton);

        continueButton.setVisible(true);
        loseFrame.setUndecorated(true);
        loseFrame.setVisible(true);
    }

    /**
     * Set info for all JLabels
     */
    private void importLabels(){
        died = new JLabel("You Died");
        died.setForeground(Color.black);
        died.setFont(pixelMplus.deriveFont(60f));
        Dimension diedSize = died.getPreferredSize();
        died.setBounds(140, 20, diedSize.width, diedSize.height);

        top1 = new JLabel("Your score: "+finalLevel);
        top1.setForeground(Color.black);
        top1.setFont(pixelMplus.deriveFont(30f));
        Dimension top1Size = top1.getPreferredSize();
        top1.setBounds(140, 150, top1Size.width, top1Size.height);

        top2 = new JLabel("Visit website for highscore list");
        top2.setForeground(Color.black);
        top2.setFont(pixelMplus.deriveFont(20f));
        Dimension top2Size = top2.getPreferredSize();
        top2.setBounds(100, 260, top2Size.width, top2Size.height);

        continueButton = new JButton("Fade away");
        continueButton.setSize(300, 100);
        continueButton.setLocation(100, 300);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(35f));
        continueButton.setBackground(Color.darkGray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
    }

    /**
     * Import font pixelMplus
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}