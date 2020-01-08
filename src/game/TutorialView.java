package game;

import javax.swing.*;

public class TutorialView {

    JFrame tutorialJFrame = new JFrame();
    JButton nextPic = new JButton();

    public void tutorialFrame(){

        tutorialJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tutorialJFrame.setSize(1920, 1080);
        tutorialJFrame.setLayout(null);
        tutorialJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tutorialJFrame.setUndecorated(true);

        ImageIcon background = new ImageIcon("Bild 1");
        tutorialJFrame.setContentPane(new JLabel(background));

        tutorialJFrame.setVisible(true);
    }
}