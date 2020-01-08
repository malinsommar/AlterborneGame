package game;

import OldClasses.Hub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoseScreen extends JFrame {

    JLabel died, forces, kingdom, who;
    JButton countinueButton;
    Font pixelMplus;
    TextField name;
    String userName;
    private int textDelay = 0;

    public void loseScreen() {
        setLayout(null);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        importFont();

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

        who = new JLabel("What is your name, hero?");
        who.setForeground(Color.black);
        who.setFont(pixelMplus.deriveFont(30f));
        Dimension whoSize = who.getPreferredSize();
        who.setBounds(460, 400, whoSize.width, whoSize.height);

        name = new TextField();
        name.setBounds(530,460,200,30);

        userName = name.getName();

        countinueButton = new JButton("Fade away");
        countinueButton.setSize(300, 100);
        countinueButton.setLocation(520, 600);
        countinueButton.setForeground(Color.white);
        countinueButton.setFont(pixelMplus.deriveFont(35f));
        countinueButton.setBackground(Color.darkGray);
        countinueButton.setBorder(null);
        countinueButton.setFocusPainted(false);

        countinueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                countinueButton.setBackground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                countinueButton.setBackground(Color.darkGray);
            }
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(2500);
        timer.start();

        countinueButton.addActionListener(e -> dispose());
        countinueButton.addActionListener(e -> new Hub());

        MusicPick.musicStart("gwyn","music");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            textDelay++;
            if (textDelay == 1){
                add(died);
            }
            else if (textDelay == 3){
                add(forces);
            }
            else if (textDelay == 5){
                add(kingdom);
            }
            else if (textDelay == 7){
                add(who);
                add(name);
                add(countinueButton);
            }
            repaint();
        }
    });

    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}