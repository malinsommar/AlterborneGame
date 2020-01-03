package game;

import OldClasses.Hub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoseScreen extends JFrame {

    JLabel youWon, wonBread;
    JButton countinueButton;
    Font pixelMplus;
    private int textDelay = 0;

    public LoseScreen() {
        setLayout(null);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picwin = new ImageIcon("C:\\Users\\96simben\\Documents\\GitHub\\Game\\newGameBackground.jpg");
        setContentPane(new JLabel(picwin));

        try {pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch(IOException | FontFormatException e){}

        youWon = new JLabel("You Died");
        youWon.setForeground(Color.white);
        youWon.setFont(pixelMplus.deriveFont(150f));
        Dimension size = youWon.getPreferredSize();
        youWon.setBounds(200, 100, size.width, size.height);

        wonBread = new JLabel("You had challenged forces too great for you");
        wonBread.setForeground(Color.white);
        wonBread.setFont(pixelMplus.deriveFont(30f));
        Dimension size4 = wonBread.getPreferredSize();
        wonBread.setBounds(200, 300, size4.width, size4.height);

        countinueButton = new JButton("Enter your name:");
        countinueButton.setSize(300, 100);
        countinueButton.setLocation(500, 600);
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
                add(youWon);
            }
            else if (textDelay == 3){
                add(wonBread);
            }
            else if (textDelay == 5){

            }
            else if (textDelay == 7){
                add(countinueButton);
            }
            repaint();
        }
    });
}