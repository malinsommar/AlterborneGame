package game;

import davidtest.overworld.hub.OverWorld;
import fight.ForestFight;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Backstory extends JFrame {

    private JLabel backStory, backStory2, backStory3, yourParty;
    private JButton startButton;
    Font pixelMplus;

    private JLabel warrior, mage, healer,ranger;
    OverWorld overWorld = new OverWorld();

    public Backstory() {

        super("New Game");
        setLayout(null);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.darkGray);
        MusicPick.musicStart("theme","music");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        importFont();
        importLabels();
        importButton();
        importGifs();

        add(warrior);
        add(ranger);
        add(mage);
        add(healer);
        add(startButton);
        add(backStory);
        add(backStory2);
        add(backStory3);
        add(yourParty);

        //ActionListeners
        startButton.addActionListener(e -> dispose());
        startButton.addActionListener(e -> overWorld.start());


        setVisible(true);
    }

    public void importLabels(){

        backStory = new JLabel("This is a backstory. very cool. This is a backstory. very cool.");
        backStory.setFont((pixelMplus.deriveFont(30f)));
        backStory.setForeground(Color.white);
        Dimension size = backStory.getPreferredSize();
        backStory.setBounds(170, 100, size.width, size.height);

        backStory2 = new JLabel("This is a backstory. very nice. This is a backstory. very nice.");
        backStory2.setFont((pixelMplus.deriveFont(30f)));
        backStory2.setForeground(Color.white);
        Dimension size2 = backStory2.getPreferredSize();
        backStory2.setBounds(170, 130, size2.width, size2.height);

        backStory3 = new JLabel("This is a backstory. very cool. This is a backstory. very cool.");
        backStory3.setFont((pixelMplus.deriveFont(30f)));
        backStory3.setForeground(Color.white);
        Dimension size3 = backStory.getPreferredSize();
        backStory3.setBounds(170, 70, size3.width, size3.height);

        yourParty = new JLabel("This is your party.");
        yourParty.setFont((pixelMplus.deriveFont(100f)));
        yourParty.setForeground(Color.white);
        Dimension size4 = yourParty.getPreferredSize();
        yourParty.setBounds(200, 220, size4.width, size4.height);
    }

    public void importButton(){

        startButton = new JButton("Save the world");
        startButton.setSize(300, 100);
        startButton.setLocation(500, 600);
        startButton.setFont((pixelMplus.deriveFont(30f)));
        startButton.setForeground(Color.darkGray);
        startButton.setBackground(Color.WHITE);
        startButton.setBorder(null); //Remove border around button
        startButton.setFocusPainted(false);//Remove border around text in button

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            //Change button color while hovering
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(Color.darkGray);
                startButton.setForeground(Color.white);
            }

            //Change back when not hovering
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(Color.white);
                startButton.setForeground(Color.darkGray);
            }
        });
    }

    public void importGifs(){
        //Create a label
        warrior = new JLabel();
        ranger = new JLabel();
        mage = new JLabel();
        healer = new JLabel();

        //Add a picture to the label
        warrior.setIcon(new ImageIcon("warrior.gif"));
        ranger.setIcon(new ImageIcon("ranger.gif"));
        mage.setIcon(new ImageIcon("mage.gif"));
        healer.setIcon(new ImageIcon("healer.gif"));

        //Get size
        Dimension warriorSize = warrior.getPreferredSize();
        Dimension rangerSize = ranger.getPreferredSize();
        Dimension mageSize = mage.getPreferredSize();
        Dimension healerSize = healer.getPreferredSize();

        //Set bounds (location and size)
        warrior.setBounds(140, 350, warriorSize.width, warriorSize.height);
        ranger.setBounds(340, 350, rangerSize.width, rangerSize.height);
        mage.setBounds(540, 350, mageSize.width, mageSize.height);
        healer.setBounds(740, 350, healerSize.width, healerSize.height);
    }

    public void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
        }
    }
}