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

    JFrame loseFrame = new JFrame();

    JLabel died, forces, kingdom, highScore, top1, top2, top3, top4, top5, top6, top7, top8, top9, top10;
    JButton continueButton;
    private Font pixelMplus;

    /**
     *Starts the lose JFrame and calls private methods needed for the frame.
     */
    public void loseScreen() {

        loseFrame.setLayout(null);
        loseFrame.setSize(1920, 1080);
        loseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        importFont();
        importLabels();

        loseFrame.add(died);
        loseFrame.add(forces);
        loseFrame.add(kingdom);
        loseFrame.add(continueButton);
        loseFrame.add(highScore);
        loseFrame.add(top1);
        loseFrame.add(top2);
        loseFrame.add(top3);
        loseFrame.add(top4);
        loseFrame.add(top5);
        loseFrame.add(top6);
        loseFrame.add(top7);
        loseFrame.add(top8);
        loseFrame.add(top9);
        loseFrame.add(top10);

        loseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loseFrame.setUndecorated(true);
        loseFrame.setVisible(true);
    }

    /**
     * Set info for all JLabels
     */
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
        forces.setBounds(200, 230, forcesSize.width, forcesSize.height);

        kingdom = new JLabel("You will be remembered as a hero, if the human race survives.");
        kingdom.setForeground(Color.black);
        kingdom.setFont(pixelMplus.deriveFont(30f));
        Dimension kingdomSize = kingdom.getPreferredSize();
        kingdom.setBounds(200, 280, kingdomSize.width, kingdomSize.height);
        
        highScore = new JLabel("The mightiest fallen heroes:");
        highScore.setForeground(Color.black);
        highScore.setFont(pixelMplus.deriveFont(30f));
        Dimension highScoreSize = highScore.getPreferredSize();
        highScore.setBounds(440, 340, highScoreSize.width, highScoreSize.height);

        top1 = new JLabel("1. Malin 10000 XP");
        top1.setForeground(Color.black);
        top1.setFont(pixelMplus.deriveFont(20f));
        Dimension top1Size = top1.getPreferredSize();
        top1.setBounds(550, 390, top1Size.width, top1Size.height);

        top2 = new JLabel("2. Simon 9999 XP");
        top2.setForeground(Color.black);
        top2.setFont(pixelMplus.deriveFont(20f));
        Dimension top2Size = top2.getPreferredSize();
        top2.setBounds(550, 410, top2Size.width, top2Size.height);

        top3 = new JLabel("3. David 9998 XP");
        top3.setForeground(Color.black);
        top3.setFont(pixelMplus.deriveFont(20f));
        Dimension top3Size = top3.getPreferredSize();
        top3.setBounds(550, 430, top3Size.width, top3Size.height);

        top4 = new JLabel("4. Lydia 9997 XP");
        top4.setForeground(Color.black);
        top4.setFont(pixelMplus.deriveFont(20f));
        Dimension top4Size = top4.getPreferredSize();
        top4.setBounds(550, 450, top4Size.width, top4Size.height);

        top5 = new JLabel("5. Shrek 9996 XP");
        top5.setForeground(Color.black);
        top5.setFont(pixelMplus.deriveFont(20f));
        Dimension top5Size = top5.getPreferredSize();
        top5.setBounds(550, 470, top5Size.width, top5Size.height);

        top6 = new JLabel("6. Shrek 9995 XP");
        top6.setForeground(Color.black);
        top6.setFont(pixelMplus.deriveFont(20f));
        Dimension top6Size = top6.getPreferredSize();
        top6.setBounds(550, 490, top6Size.width, top6Size.height);

        top7 = new JLabel("7. Shrek 9994 XP");
        top7.setForeground(Color.black);
        top7.setFont(pixelMplus.deriveFont(20f));
        Dimension top7Size = top7.getPreferredSize();
        top7.setBounds(550, 510, top7Size.width, top7Size.height);

        top8 = new JLabel("8. Shrek 9993 XP");
        top8.setForeground(Color.black);
        top8.setFont(pixelMplus.deriveFont(20f));
        Dimension top8Size = top8.getPreferredSize();
        top8.setBounds(550, 530, top8Size.width, top8Size.height);

        top9 = new JLabel("9. Shrek 9992 XP");
        top9.setForeground(Color.black);
        top9.setFont(pixelMplus.deriveFont(20f));
        Dimension top9Size = top9.getPreferredSize();
        top9.setBounds(550, 550, top9Size.width, top9Size.height);

        top10 = new JLabel("10. Shrek 9991 XP");
        top10.setForeground(Color.black);
        top10.setFont(pixelMplus.deriveFont(20f));
        Dimension top10Size = top10.getPreferredSize();
        top10.setBounds(550, 570, top10Size.width, top10Size.height);
        

        continueButton = new JButton("Fade away");
        continueButton.setSize(300, 100);
        continueButton.setLocation(480, 600);
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