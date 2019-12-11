package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hub extends JFrame {

    private JLabel gameName, gameName2;
    private JButton tutorialButton, newRunButton, exitButton;
    Font pixelMplus;

    public Hub() {

        super("Alterborne");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1920, 1080);

        importFont();
        importNewGameButtons();
        hover();

        //Background picture
        ImageIcon background = new ImageIcon("bakgrundMountain.png"); //download image
        setContentPane(new JLabel(background)); //set backgrund

        //Title of game
        gameName = new JLabel("Alterborne");
        gameName.setForeground(Color.white);
        gameName.setFont((pixelMplus.deriveFont(100f)));
        Dimension size = gameName.getPreferredSize();
        gameName.setBounds(400, 30, size.width, size.height);

        add(gameName);
        add(newRunButton);
        add(tutorialButton);
        add(exitButton);

        newRunButton.addActionListener(e -> dispose());
        newRunButton.addActionListener(e -> new Backstory());
        exitButton.addActionListener(e -> System.exit(0));

        MusicPick.musicStart("mainmenu","music");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public void importNewGameButtons(){
        //New run Button
        newRunButton = new JButton("New Game");
        newRunButton.setSize(300, 100);
        newRunButton.setLocation(500, 240);
        newRunButton.setFont((pixelMplus.deriveFont(30f)));
        newRunButton.setBackground(Color.white);
        newRunButton.setBorder(null); //Remove border around button
        newRunButton.setFocusPainted(false);//Remove border around text in button

        //Tutorial Button
        tutorialButton = new JButton("Tutorial");
        tutorialButton.setSize(300, 100);
        tutorialButton.setLocation(500, 360);
        tutorialButton.setFont((pixelMplus.deriveFont(30f)));
        tutorialButton.setBackground(Color.white);
        tutorialButton.setBorder(null); //Remove border around button
        tutorialButton.setFocusPainted(false);//Remove border around text in button


        //Exit Button
        exitButton = new JButton("Exit game");
        exitButton.setSize(300, 100);
        exitButton.setLocation(500, 480);
        exitButton.setFont((pixelMplus.deriveFont(30f)));
        exitButton.setBackground(Color.white);
        exitButton.setBorder(null); //Remove border around button
        exitButton.setFocusPainted(false);//Remove border around text in button
    }

    public void hover(){

        //new run button
        newRunButton.addMouseListener(new java.awt.event.MouseAdapter() {
            //Change button color while hovering
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newRunButton.setBackground(Color.lightGray);
            }
            //Change back when exiting
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newRunButton.setBackground(Color.white);
            }
        });

        //Tutorial Button
        tutorialButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tutorialButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tutorialButton.setBackground(Color.white);
            }
        });

        //Exit button
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(Color.white);
            }
        });
    }

    public void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
        }
    }
}