package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LootScreen extends JFrame {

    Hub hub = new Hub();

    JButton countinueButton;
    JLabel vicTitle;
    int textDelay = 0;

    public LootScreen(int fight){

        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 400);
        setTitle("Forest Fight");

        //Label
        vicTitle = new JLabel("Victory Achieved");
        vicTitle.setForeground(Color.black);
        vicTitle.setFont((hub.pixelMplus.deriveFont(30f)));
        Dimension size = vicTitle.getPreferredSize();
        vicTitle.setBounds(200, 30, size.width, size.height);

        //Button
        countinueButton = new JButton("Onwards!");
        countinueButton.setSize(300, 100);
        countinueButton.setLocation(100, 300);
        countinueButton.setForeground(Color.white);
        countinueButton.setFont(hub.pixelMplus.deriveFont(30f));
        countinueButton.setBackground(Color.darkGray);
        countinueButton.setBorder(null);
        countinueButton.setFocusPainted(false);

        add(countinueButton);
        add(vicTitle);

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(500);
        timer.start();

        //ActionListeners
        countinueButton.addActionListener(e -> dispose());

        MusicPick.musicStart("Victory","music");

        setUndecorated(true);
        setVisible(true);
    }

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            textDelay++;
            if (textDelay == 1){
                MusicPick.musicStart("ding","");
                add(vicTitle);
            }
          /*
                xp
                gold
                item

            }
            else if (textDelay == 6){
                MusicPick.musicStart("ding","");
                add(countinueButton);
            }*/
            repaint();
        }
    });

    public void hover(){
        countinueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                countinueButton.setBackground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                countinueButton.setBackground(Color.darkGray);
            }
        });
    }
}