package OldClasses;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ForestBossFight extends JFrame implements KeyListener {
    Font pixelMplus;
    JButton attackButton;
    JButton blockButton;
    JButton itemButton;
    JButton skillButton;
    JButton fireballButton;
    JButton firestormButton;
    JButton arcaneexplosionButton;
    JButton pyroblastButton;

    //JLabel attacked;


    public void importFont() {

        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));

        } catch (IOException | FontFormatException e) {

        }
    }

    public ForestBossFight(){

        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1920, 1080);
        setTitle("Forest Fight");
        importFont();

        //Background picture
        ImageIcon background = new ImageIcon("forest.jpg"); //download image
        setContentPane(new JLabel(background)); //set backgrund

        //Boss gif
        JLabel boss = new JLabel();
        boss.setIcon(new ImageIcon("forestBoss.gif"));
        Dimension bossSize = boss.getPreferredSize();
        boss.setBounds(800, 90, bossSize.width, bossSize.height);

        //Party members
        JLabel warrior = new JLabel();
        warrior.setIcon(new ImageIcon("warrior.gif"));
        Dimension warriorSize = warrior.getPreferredSize();
        warrior.setBounds(170, 210, warriorSize.width, warriorSize.height);

        JLabel healer = new JLabel();
        healer.setIcon(new ImageIcon("healer.gif"));
        Dimension healerSize = healer.getPreferredSize();
        healer.setBounds(-30, 210, healerSize.width, healerSize.height);

        JLabel ranger = new JLabel();
        ranger.setIcon(new ImageIcon("ranger.gif"));
        Dimension rangerSize = ranger.getPreferredSize();
        ranger.setBounds(70, 290, rangerSize.width, rangerSize.height);

        JLabel mage = new JLabel();
        mage.setIcon(new ImageIcon("mage.gif"));
        Dimension mageSize = mage.getPreferredSize();
        mage.setBounds(-110, 290, mageSize.width, mageSize.height);


        //Buttons
        attackButton = new JButton("Attack");
        attackButton.setSize(200, 70);
        attackButton.setLocation(840, 535);
        attackButton.setFont(pixelMplus);
        attackButton.setBackground(Color.white);
        attackButton.setBorder(null); //Remove border around button
        attackButton.setFocusPainted(false);//Remove border around text in button

        blockButton = new JButton("Block");
        blockButton.setSize(200, 70);
        blockButton.setLocation(1050, 535);
        blockButton.setFont(pixelMplus);
        blockButton.setBackground(Color.white);
        blockButton.setBorder(null); //Remove border around button
        blockButton.setFocusPainted(false);//Remove border around text in button

        itemButton = new JButton("Item");
        itemButton.setSize(200, 70);
        itemButton.setLocation(1050, 610);
        itemButton.setFont(pixelMplus);
        itemButton.setBackground(Color.white);
        itemButton.setBorder(null); //Remove border around button
        itemButton.setFocusPainted(false);//Remove border around text in button

        skillButton = new JButton("Skill");
        skillButton.setSize(200, 70);
        skillButton.setLocation(840, 610);
        skillButton.setFont(pixelMplus);
        skillButton.setBackground(Color.white);
        skillButton.setBorder(null); //Remove border around button
        skillButton.setFocusPainted(false);//Remove border around text in button

        //

        fireballButton = new JButton("Magic Missile");
        fireballButton.setSize(200, 70);
        fireballButton.setLocation(840, 535);
        fireballButton.setFont(pixelMplus);
        fireballButton.setBackground(Color.white);
        fireballButton.setBorder(null); //Remove border around button
        fireballButton.setFocusPainted(false);//Remove border around text in button

        arcaneexplosionButton = new JButton("Arcane Blast");
        arcaneexplosionButton.setSize(200, 70);
        arcaneexplosionButton.setLocation(840, 610);
        arcaneexplosionButton.setFont(pixelMplus);
        arcaneexplosionButton.setBackground(Color.white);
        arcaneexplosionButton.setBorder(null); //Remove border around button
        arcaneexplosionButton.setFocusPainted(false);//Remove border around text in button

        firestormButton = new JButton("Firestorm");
        firestormButton.setSize(200, 70);
        firestormButton.setLocation(1050, 535);
        firestormButton.setFont(pixelMplus);
        firestormButton.setBackground(Color.white);
        firestormButton.setBorder(null); //Remove border around button
        firestormButton.setFocusPainted(false);//Remove border around text in button

        pyroblastButton = new JButton("Pyroblast");
        pyroblastButton.setSize(200, 70);
        pyroblastButton.setLocation(1050, 610);
        pyroblastButton.setFont(pixelMplus);
        pyroblastButton.setBackground(Color.white);
        pyroblastButton.setBorder(null); //Remove border around button
        pyroblastButton.setFocusPainted(false);//Remove border around text in button


        //Add Items
        add(attackButton);
        add(blockButton);
        add(itemButton);
        add(skillButton);
        add(fireballButton);
        add(firestormButton);
        add(arcaneexplosionButton);
        add(pyroblastButton);

        add(boss);

        add(ranger);
        add(warrior);
        add(mage);
        add(healer);

        //Attack ActionListeners
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            //Change button color while hovering
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.lightGray);
            }
            //Change back when not hovering
            public void mouseExited(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.white);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                boss.setLocation(5000, 5000);

            }
        });

        //Block ActionListeners
        blockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                blockButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                blockButton.setBackground(Color.white);
            }
        });

        //Item ActionListeners
        itemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemButton.setBackground(Color.white);
            }
        });

        //Skill ActionListeners
        skillButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                skillButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { skillButton.setBackground(Color.white); }
        });

/* plan b, button för att gå bakåt
        fireballButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.white);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backmenu(); }
        });
 */


        skillButton.addActionListener(e -> skillmenu());

        setFocusable(true);

        addKeyListener(this);

        MusicPick.musicStart("forest2","music");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public void skillmenu(){
        attackButton.setVisible(false);
        blockButton.setVisible(false);
        itemButton.setVisible(false);
        skillButton.setVisible(false);
        fireballButton.setVisible(true);
        firestormButton.setVisible(true);
        arcaneexplosionButton.setVisible(true);
        pyroblastButton.setVisible(true);
    }
    public void backmenu() {
        attackButton.setVisible(true);
        blockButton.setVisible(true);
        itemButton.setVisible(true);
        skillButton.setVisible(true);
        fireballButton.setVisible(false);
        firestormButton.setVisible(false);
        arcaneexplosionButton.setVisible(false);
        pyroblastButton.setVisible(false);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //if(keyEvent.getKeyCode() == KeyEvent.VK_E)
        backmenu();
    }
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        backmenu();
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        backmenu();
    }
}
