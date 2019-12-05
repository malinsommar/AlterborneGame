package simontest;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simontest extends JFrame {

    private JButton shootarrowbutton, tacklebutton, takedamagebutton, magespellbutton, chargebutton, volleybutton;

    public int cloudx = 100;
    public int cloudy = 200;
    public int startpostitionwarriorx = cloudx;
    public int startpostitionwarriory = cloudx;
    int phase = 0;
    int timepast = 0;


    JLabel player1 = new JLabel(new ImageIcon("mage.gif"));

    public simontest() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picShop = new ImageIcon("C:\\Users\\96simben\\Documents\\GitHub\\Game\\src\\Gametest\\Simontest\\shopk.jpg");
        setContentPane(new JLabel(picShop)); //assign background

        Dimension warriorSize = player1.getPreferredSize();
        player1.setSize(warriorSize.width, warriorSize.height);
        player1.setLocation(cloudx, cloudy);
        add(player1);

        tacklebutton = new JButton("move");
        tacklebutton.setSize(150, 40);
        tacklebutton.setLocation(800, 150);
        tacklebutton.setBackground(Color.white);
        add(tacklebutton);
        tacklebutton.addActionListener(e -> {
            unusedtakedamage.start();
        });
        /* onödiga timer saker?
        tackle.setRepeats(true);
        tackle.setCoalesce(true);
        tackle.setInitialDelay(10);
         */


        MusicPick.musicStart("allstar", "music");
        setLayout(null); //Default layout
        setSize(1900, 500);

        setVisible(true);

    }

    Timer unusedtakedamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("warriorattacked", "");
                phase = 1;
            } else if (phase == 1) {
                timepast++;
                if (timepast > 100) {
                    timepast = 0;
                    phase = 2;
                }
            } else if (phase == 2) {
                player1.setVisible(false);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                player1.setVisible(true);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 4;
                }
            } else if (phase == 4) {
                player1.setVisible(false);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 5;
                }
            } else if (phase == 5) {
                player1.setVisible(true);
                unusedtakedamage.stop();
                phase = 0;
            }
        }
    });
}