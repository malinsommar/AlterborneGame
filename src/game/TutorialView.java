package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * TutorialView contains all swing variables to start TutorialFrame.
 *
 * @author Malin Sommar
 * @version 1
 */
public class TutorialView {

    JFrame tutorialJFrame = new JFrame();
    JButton nextPic = new JButton();
    Font pixelMplus;

    JLabel picture,text1,text2,text3;

    /**
     * Starts the tutorial JFrame and calls private methods needed for the frame.
     */
    public void tutorialFrame(){

        tutorialJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tutorialJFrame.setSize(1920, 1080);
        tutorialJFrame.setLayout(null);
        tutorialJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tutorialJFrame.setUndecorated(true);

        importFont();
        addLabelsButtons();

        ImageIcon background = new ImageIcon("fight.png");
        picture = new JLabel(background);
        tutorialJFrame.setContentPane(picture);

        tutorialJFrame.add(nextPic);
        tutorialJFrame.add(text1);
        tutorialJFrame.add(text2);
        tutorialJFrame.add(text3);


        tutorialJFrame.setVisible(true);
    }

    /**
     * Set info for all JLabels and buttons
     */
    private void addLabelsButtons(){

        nextPic = new JButton("Next");
        nextPic.setSize(300, 100);
        nextPic.setLocation(500, 140);
        nextPic.setForeground(Color.black);
        nextPic.setFont(pixelMplus.deriveFont(35f));
        nextPic.setBackground(Color.white);
        nextPic.setBorder(null);
        nextPic.setFocusPainted(false);

        text1 = new JLabel("AlterBorne Tutorial.                               ");
        text1.setForeground(Color.black);
        text1.setFont(pixelMplus.deriveFont(20f));
        Dimension diedSize = text1.getPreferredSize();
        text1.setBounds(550, 20, diedSize.width, diedSize.height);

        text2 = new JLabel("This is the forest fight.                           ");
        text2.setForeground(Color.black);
        text2.setFont(pixelMplus.deriveFont(20f));
        Dimension forcesSize = text2.getPreferredSize();
        text2.setBounds(530, 55, forcesSize.width, forcesSize.height);

        text3 = new JLabel("Press 'Next' for next picture.                     ");
        text3.setForeground(Color.black);
        text3.setFont(pixelMplus.deriveFont(20f));
        Dimension kingdomSize = text3.getPreferredSize();
        text3.setBounds(505, 90, kingdomSize.width, kingdomSize.height);

    }

    /**
     * Imports font pixelMplus
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}