package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * HubView contains all swing variables to start hubFrame.
 *
 * @author Malin Sommar
 *
 * @version 1
 */
public class HubView extends JPanel {

    public JFrame hubJFrame = new JFrame();
    public JPanel hubPanel = new JPanel();
    public JPanel BackStoryPanel = new JPanel();


    private ImageIcon background = new ImageIcon("backgroundMountain.png");
    private JLabel image = new JLabel(background);

    private JLabel gameName, backStory, backStory2, backStory3, backStory4, yourParty, warrior, mage, healer,ranger;
    public JButton tutorialButton, newRunButton, exitButton, startButton;
    private Font pixelMplus;

    /**
     *Starts the hub JFrame and calls private methods needed for the frame.
     */
    //This method open Menu panel or backstory panel.
    public void hubFrame(){

        hubJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hubJFrame.setSize(1920, 1080);
        hubJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        hubJFrame.setUndecorated(true);

        hubPanel.setBounds(0,0,1920, 1080);
        hubPanel.setLayout(null);
        hubPanel.setBackground(Color.black);

        BackStoryPanel.setBounds(0,0,1920, 1080);
        BackStoryPanel.setLayout(null);
        BackStoryPanel.setBackground(Color.darkGray);

        MusicPick.musicStart("mainmenu","music");

        ImageIcon icon = new ImageIcon("backgroundmountain.png");
        JLabel backgroundImage = new JLabel();
        backgroundImage.setIcon(icon);
        backgroundImage.setBounds(1,-200,1920,1080);

        importFont();
        importHubButtonsLabels();
        importBackstoryButtonsLabels();
        importBackstoryGifs();
        hover();

        hubPanel.add(gameName);
        hubPanel.add(newRunButton);
        hubPanel.add(tutorialButton);
        hubPanel.add(exitButton);
        hubPanel.add(image);
        hubJFrame.add(hubPanel);

        BackStoryPanel.add(warrior);
        BackStoryPanel.add(ranger);
        BackStoryPanel.add(mage);
        BackStoryPanel.add(healer);
        BackStoryPanel.add(startButton);
        BackStoryPanel.add(backStory);
        BackStoryPanel.add(backStory2);
        BackStoryPanel.add(backStory3);
        BackStoryPanel.add(backStory4);
        BackStoryPanel.add(yourParty);

        hubPanel.add(backgroundImage);
        hubJFrame.setVisible(true);
    }

    /**
     * Set info for all JLabels
     */
    //Import buttons and labels for the hub/main menu.
    private void importHubButtonsLabels(){

        //Title of game
        gameName = new JLabel("AlterBorne");
        gameName.setForeground(Color.white);
        gameName.setFont((pixelMplus.deriveFont(100f)));
        Dimension size = gameName.getPreferredSize();
        gameName.setBounds(400, 30, size.width, size.height);

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

    /**
     * Import gifs of party members.
     */
    //Import buttons and labels for the .
    private void importBackstoryGifs(){
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
        warrior.setBounds(120, 350, warriorSize.width, warriorSize.height);
        ranger.setBounds(320, 350, rangerSize.width, rangerSize.height);
        mage.setBounds(520, 350, mageSize.width, mageSize.height);
        healer.setBounds(720, 350, healerSize.width, healerSize.height);
    }

    /**
     * Set labels on backstoryPanel
     */
    private void importBackstoryButtonsLabels(){

        startButton = new JButton("Save the world");
        startButton.setSize(300, 100);
        startButton.setLocation(500, 600);
        startButton.setFont((pixelMplus.deriveFont(30f)));
        startButton.setForeground(Color.darkGray);
        startButton.setBackground(Color.WHITE);
        startButton.setBorder(null); //Remove border around button
        startButton.setFocusPainted(false);//Remove border around text in button

        backStory = new JLabel("In the heart the great land of Geru'xelm, the seat of power is in danger.");
        backStory.setFont((pixelMplus.deriveFont(30f)));
        backStory.setForeground(Color.white);
        Dimension size = backStory.getPreferredSize();
        backStory.setBounds(70, 70, size.width, size.height);

        backStory2 = new JLabel("There was a foe of a thousand demons, who paved the way for your damnation.");
        backStory2.setFont((pixelMplus.deriveFont(30f)));
        backStory2.setForeground(Color.white);
        Dimension size2 = backStory2.getPreferredSize();
        backStory2.setBounds(60, 105, size2.width, size2.height);

        backStory3 = new JLabel("189 soldiers, in the service of the king. Protecting the kingdom.");
        backStory3.setFont((pixelMplus.deriveFont(30f)));
        backStory3.setForeground(Color.white);
        Dimension size3 = backStory.getPreferredSize();
        backStory3.setBounds(150, 140, size3.width, size3.height);

        backStory4 = new JLabel("Gave their lives for glory. Only 4 survived.");
        backStory4.setFont((pixelMplus.deriveFont(30f)));
        backStory4.setForeground(Color.white);
        backStory4.setBounds(290, 175, size3.width, size3.height);

        yourParty = new JLabel("This is your party");
        yourParty.setFont((pixelMplus.deriveFont(100f)));
        yourParty.setForeground(Color.white);
        Dimension size4 = yourParty.getPreferredSize();
        yourParty.setBounds(200, 220, size4.width, size4.height);

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

    /**
     * Adds mousListeners to buttons
     */
    private void hover(){

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

    /**
     * Import font pixelMplus.
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}