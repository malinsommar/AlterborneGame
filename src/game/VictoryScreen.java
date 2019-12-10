package game;

import davidtest.overworld.hub.OverWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VictoryScreen extends JFrame {

    JLabel youWon, wonBread;
    JButton continueButton;
    Font pixelMplus;
    OverWorld overWorld;
    private int textDelay = 0;

    public VictoryScreen() {
        setLayout(null);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picwin = new ImageIcon("C:\\Users\\96simben\\Documents\\GitHub\\Game\\newGameBackground.jpg");
        setContentPane(new JLabel(picwin));

        try {pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch(IOException | FontFormatException e){}

        youWon = new JLabel("Victory Achieved");
        youWon.setForeground(Color.white);
        youWon.setFont(pixelMplus.deriveFont(100f));
        Dimension size = youWon.getPreferredSize();
        youWon.setBounds(200, 100, size.width, size.height);

        wonBread = new JLabel("The minions of the underworld did not best you this day");
        wonBread.setForeground(Color.white);
        wonBread.setFont(pixelMplus.deriveFont(30f));
        Dimension size4 = wonBread.getPreferredSize();
        wonBread.setBounds(200, 300, size4.width, size4.height);

        continueButton = new JButton("Onwards!");
        continueButton.setSize(300, 100);
        continueButton.setLocation(500, 600);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(50f));
        continueButton.setBackground(Color.darkGray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);

        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(Color.darkGray);
            }
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(500);
        timer.start();

        continueButton.addActionListener(e -> System.out.println("Wowi"));
        continueButton.addActionListener(e -> dispose());


        MusicPick.musicStart("Victory","music");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            textDelay++;
            if (textDelay == 1){
                MusicPick.musicStart("ding","");
                add(youWon);
            }
            else if (textDelay == 3){
                MusicPick.musicStart("ding","");
                add(wonBread);
            }
            else if (textDelay == 6){
                MusicPick.musicStart("ding","");
                add(continueButton);
            }
            repaint();
        }
    });
}