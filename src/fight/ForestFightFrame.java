package fight;

import imports.Potions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Malin Sommar, Simon Bengtsson
 */
public class ForestFightFrame {

    JPanel inventory = new JPanel();
    JFrame forestFightJFrame = new JFrame();
    Potions potions = new Potions();

    //Create fonts
    Font pixelMplus;

    //Create buttons
    public JButton attackButton, blockButton, itemButton, skillButton, endTurnButton;
    public JButton potion1, potion2, potion3, potion4, potion5, potion6, potion7, potion8, potion9, potion10, potion11, potion12;
    public JButton skill1Button, skill2Button, skill3Button, skill4Button, returnButton; //spells
    public JButton healWarriorButton, healRangerButton, healMageButton, healHealerButton; //healing target button

    //Create Labels
    public JLabel inventoryHealth, inventoryBlock, inventoryEnergy, inventoryStr ,potion1Label,potion2Label,potion3Label,potion4Label,potion5Label,potion6Label,potion7Label,potion8Label,potion9Label,potion10Label,potion11Label,potion12Label;
    public JLabel whosTurn, energy, block;
    public JLabel wolf1, wolf2, wolf3, wolf4;
    public JLabel wolf1Hp, wolf2Hp, wolf3Hp, wolf4Hp;
    public JLabel warrior, mage, healer, ranger;
    public JLabel playerStr, player1Hp, player2Hp, player3Hp, player4Hp;

    public JLabel arrow = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley1 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley2 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley3 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel flame = new JLabel(new ImageIcon("flame.gif"));
    public JLabel mediumPyroBlast = new JLabel(new ImageIcon("mediumflame.gif"));
    public JLabel smallPyroBlast = new JLabel(new ImageIcon("miniflame.gif"));
    public JLabel bigPyroBlast = new JLabel(new ImageIcon("flame.gif"));
    public JLabel swordIcon = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel upArrow = new JLabel(new ImageIcon("uparrow.png"));
    public JLabel demoSword1 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword2 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword3 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword4 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel bomb = new JLabel(new ImageIcon("bomb.png"));
    public JLabel explode = new JLabel(new ImageIcon("explode.gif"));
    public JLabel stealthranger = new JLabel(new ImageIcon("stealth ranger.gif"));
    public JLabel trap = new JLabel(new ImageIcon("trap.png"));
    public JLabel holyLight = new JLabel(new ImageIcon("holylight.png"));
    public JLabel smallHolyLight = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal1 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal2 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal3 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal4 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel targetarrow = new JLabel(new ImageIcon("targetarrow.png"));
    public JLabel blast = new JLabel(new ImageIcon("blastgif.gif"));

    /**
     *
     */
    public void forestFightFrame(){

        forestFightJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        forestFightJFrame.setSize(1920, 1080);
        forestFightJFrame.setLayout(null);
        forestFightJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        forestFightJFrame.setUndecorated(true);

        ImageIcon background = new ImageIcon("forest.jpg");
        forestFightJFrame.setContentPane(new JLabel(background));

        inventory.setLayout(null);
        inventory.setSize(1920, 170);
        inventory.setLocation(0,0);

        importFont();
        getInventory();
        importButtons();
        importLabels();
        importPartyGif();
        importWolfGif();
        spellMenuStartup();
        animationStuff();
        healingTarget();
        getInventory();

        forestFightJFrame.add(energy);
        forestFightJFrame.add(block);
        forestFightJFrame.add(whosTurn);

        forestFightJFrame.add(targetarrow);

        forestFightJFrame.add(attackButton);
        forestFightJFrame.add(blockButton);
        forestFightJFrame.add(itemButton);
        forestFightJFrame.add(skillButton);
        forestFightJFrame.add(endTurnButton);

        forestFightJFrame.add(playerStr);
        forestFightJFrame.add(wolf1Hp);
        forestFightJFrame.add(wolf2Hp);
        forestFightJFrame.add(wolf3Hp);
        forestFightJFrame.add(wolf4Hp);
        forestFightJFrame.add(player1Hp);
        forestFightJFrame.add(player2Hp);
        forestFightJFrame.add(player3Hp);
        forestFightJFrame.add(player4Hp);

        forestFightJFrame.add(skill1Button);
        forestFightJFrame.add(skill2Button);
        forestFightJFrame.add(skill3Button);
        forestFightJFrame.add(skill4Button);
        forestFightJFrame.add(returnButton);

        forestFightJFrame.add(healWarriorButton);
        forestFightJFrame.add(healRangerButton);
        forestFightJFrame.add(healMageButton);
        forestFightJFrame.add(healHealerButton);

        forestFightJFrame.add(arrow);
        forestFightJFrame.add(volley1);
        forestFightJFrame.add(volley2);
        forestFightJFrame.add(volley3);
        forestFightJFrame.add(blast);
        forestFightJFrame.add(flame);
        forestFightJFrame.add(smallPyroBlast);
        forestFightJFrame.add(mediumPyroBlast);
        forestFightJFrame.add(bigPyroBlast);
        forestFightJFrame.add(swordIcon);
        forestFightJFrame.add(demoSword1);
        forestFightJFrame.add(demoSword2);
        forestFightJFrame.add(demoSword3);
        forestFightJFrame.add(demoSword4);
        forestFightJFrame.add(bomb);
        forestFightJFrame.add(explode);
        forestFightJFrame.add(stealthranger);
        forestFightJFrame.add(trap);
        forestFightJFrame.add(holyLight);
        forestFightJFrame.add(smallHolyLight);
        forestFightJFrame.add(groupHeal1);
        forestFightJFrame.add(groupHeal2);
        forestFightJFrame.add(groupHeal3);
        forestFightJFrame.add(groupHeal4);

        forestFightJFrame.add(wolf3);
        forestFightJFrame.add(wolf4);
        forestFightJFrame.add(wolf1);
        forestFightJFrame.add(wolf2);

        forestFightJFrame.add(ranger);
        forestFightJFrame.add(warrior);
        forestFightJFrame.add(mage);
        forestFightJFrame.add(healer);

        //Add all labels//buttons.
        inventory.add(potion1);
        inventory.add(potion2);
        inventory.add(potion3);
        inventory.add(potion4);
        inventory.add(potion5);
        inventory.add(potion6);
        inventory.add(potion7);
        inventory.add(potion8);
        inventory.add(potion9);
        inventory.add(potion10);
        inventory.add(potion11);
        inventory.add(potion12);
        inventory.add(inventoryHealth);
        inventory.add(inventoryBlock);
        inventory.add(inventoryEnergy);
        inventory.add(inventoryStr);
        inventory.add(potion1Label);
        inventory.add(potion2Label);
        inventory.add(potion3Label);
        inventory.add(potion4Label);
        inventory.add(potion5Label);
        inventory.add(potion6Label);
        inventory.add(potion7Label);
        inventory.add(potion8Label);
        inventory.add(potion9Label);
        inventory.add(potion10Label);
        inventory.add(potion11Label);
        inventory.add(potion12Label);

        forestFightJFrame.add(inventory);

        inventory.setVisible(false);
        forestFightJFrame.setVisible(true);
    }

    /**
     *
     */
    public void animationStuff(){

        Dimension arrowSize = arrow.getPreferredSize();
        arrow.setSize(arrowSize.width, arrowSize.height);
        arrow.setLocation(-100, 120);

        volley1.setSize(arrowSize.width, arrowSize.height);
        volley1.setLocation(-100, 120);
        volley1.setVisible(false);

        volley2.setSize(arrowSize.width, arrowSize.height);
        volley2.setLocation(-100, 120);
        volley2.setVisible(false);

        volley3.setSize(arrowSize.width, arrowSize.height);
        volley3.setLocation(-100, 120);
        volley3.setVisible(false);

        Dimension blastSize = blast.getPreferredSize();
        blast.setSize(blastSize.width, blastSize.height);
        blast.setLocation(100, 121);
        blast.setVisible(false);

        Dimension flameSize = flame.getPreferredSize();
        flame.setSize(flameSize.width, flameSize.height);
        flame.setLocation(900, -400);
        flame.setVisible(true);

        Dimension smallPyroBlastSize = smallPyroBlast.getPreferredSize();
        smallPyroBlast.setSize(smallPyroBlastSize.width, smallPyroBlastSize.height);
        smallPyroBlast.setLocation(125, 300);
        smallPyroBlast.setVisible(false);

        Dimension mediumPyroBlastSize = mediumPyroBlast.getPreferredSize();
        mediumPyroBlast.setSize(mediumPyroBlastSize.width, mediumPyroBlastSize.height);
        mediumPyroBlast.setLocation(85, 200);
        mediumPyroBlast.setVisible(false);

        Dimension bigPyroBlastSize = bigPyroBlast.getPreferredSize();
        bigPyroBlast.setSize(bigPyroBlastSize.width, bigPyroBlastSize.height);
        bigPyroBlast.setLocation(45, 150);
        bigPyroBlast.setVisible(false);

        Dimension swordIconSize = swordIcon.getPreferredSize();
        swordIcon.setSize(swordIconSize.width, swordIconSize.height);
        swordIcon.setLocation(125, 300);
        swordIcon.setVisible(false);

        Dimension demoSword1Size = demoSword1.getPreferredSize();
        demoSword1.setSize(demoSword1Size.width, demoSword1Size.height);
        demoSword1.setLocation(125, 300);
        demoSword1.setVisible(false);

        Dimension demoSword2Size = demoSword2.getPreferredSize();
        demoSword2.setSize(demoSword2Size.width, demoSword2Size.height);
        demoSword2.setLocation(125, 300);
        demoSword2.setVisible(false);

        Dimension demoSword3Size = demoSword3.getPreferredSize();
        demoSword3.setSize(demoSword3Size.width, demoSword3Size.height);
        demoSword3.setLocation(125, 300);
        demoSword3.setVisible(false);

        Dimension demoSword4Size = demoSword4.getPreferredSize();
        demoSword4.setSize(demoSword4Size.width, demoSword4Size.height);
        demoSword4.setLocation(125, 300);
        demoSword4.setVisible(false);

        Dimension bombSize = bomb.getPreferredSize();
        bomb.setSize(bombSize.width, bombSize.height);
        bomb.setLocation(250, 300);
        bomb.setVisible(false);

        Dimension explodeSize = explode.getPreferredSize();
        explode.setSize(explodeSize.width, explodeSize.height);
        explode.setLocation(900, 250);
        explode.setVisible(false);

        Dimension stealthrangerSize = stealthranger.getPreferredSize();
        stealthranger.setSize(stealthrangerSize.width, stealthrangerSize.height);
        stealthranger.setLocation(70, 290);
        stealthranger.setVisible(false);

        Dimension trapSize = trap.getPreferredSize();
        trap.setSize(trapSize.width, trapSize.height);
        trap.setLocation(70, 290);
        trap.setVisible(false);

        Dimension holyLightSize = holyLight.getPreferredSize();
        holyLight.setSize(holyLightSize.width, holyLightSize.height);
        holyLight.setLocation(70, 290);
        holyLight.setVisible(false);

        Dimension smallHolyLightSize = holyLight.getPreferredSize();
        smallHolyLight.setSize(smallHolyLightSize.width, smallHolyLightSize.height);
        smallHolyLight.setLocation(70, 290);
        smallHolyLight.setVisible(false);

        Dimension groupHealSize = groupHeal1.getPreferredSize();
        groupHeal1.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal1.setLocation(70, 290);
        groupHeal1.setVisible(false);

        groupHeal2.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal2.setLocation(70, 290);
        groupHeal2.setVisible(false);

        groupHeal3.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal3.setLocation(70, 290);
        groupHeal3.setVisible(false);

        groupHeal4.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal4.setLocation(70, 290);
        groupHeal4.setVisible(false);

        Dimension targetarrowSize = targetarrow.getPreferredSize();
        targetarrow.setSize(targetarrowSize.width, targetarrowSize.height);
        targetarrow.setLocation(200, 500);
        targetarrow.setVisible(false);

    }

    /**
     *
     */
    public void spellMenuStartup(){

        //button 1
        skill1Button = new JButton("error skill 1           ");
        skill1Button.setSize(200, 70);
        skill1Button.setLocation(840, 555);
        skill1Button.setFont(pixelMplus.deriveFont(30f));
        skill1Button.setBackground(Color.white);
        skill1Button.setBorder(null); //Remove border around button
        skill1Button.setFocusPainted(false);//Remove border around text in button

        //button 2
        skill2Button = new JButton("error skill 2           ");
        skill2Button.setSize(200, 70);
        skill2Button.setLocation(1050, 555);
        skill2Button.setFont(pixelMplus.deriveFont(30f));
        skill2Button.setBackground(Color.white);
        skill2Button.setBorder(null); //Remove border around button
        skill2Button.setFocusPainted(false);//Remove border around text in button

        //button 3
        skill3Button = new JButton("error skill 3                ");
        skill3Button.setSize(200, 70);
        skill3Button.setLocation(1050, 630);
        skill3Button.setFont(pixelMplus.deriveFont(30f));
        skill3Button.setBackground(Color.white);
        skill3Button.setBorder(null); //Remove border around button
        skill3Button.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        skill4Button = new JButton("error skill 4            ");
        skill4Button.setSize(200, 70);
        skill4Button.setLocation(840, 630);
        skill4Button.setFont(pixelMplus.deriveFont(30f));
        skill4Button.setBackground(Color.white);
        skill4Button.setBorder(null); //Remove border around button
        skill4Button.setFocusPainted(false);//Remove border around text in button

        returnButton = new JButton("Return");
        returnButton.setSize(200, 70);
        returnButton.setLocation(540, 468);
        returnButton.setFont(pixelMplus.deriveFont(30f));
        returnButton.setBackground(Color.white);
        returnButton.setBorder(null); //Remove border around button
        returnButton.setFocusPainted(false);//Remove border around text in button
        returnButton.setVisible(false);

    }

    /**
     *
     */
    //to select target for healing
    public void healingTarget(){

        //button 1
        healWarriorButton = new JButton("Warrior");
        healWarriorButton.setSize(200, 70);
        healWarriorButton.setLocation(840, 555);
        healWarriorButton.setFont(pixelMplus.deriveFont(30f));
        healWarriorButton.setBackground(Color.white);
        healWarriorButton.setBorder(null); //Remove border around button
        healWarriorButton.setFocusPainted(false);//Remove border around text in button

        //button 2
        healRangerButton = new JButton("Ranger");
        healRangerButton.setSize(200, 70);
        healRangerButton.setLocation(1050, 555);
        healRangerButton.setFont(pixelMplus.deriveFont(30f));
        healRangerButton.setBackground(Color.white);
        healRangerButton.setBorder(null); //Remove border around button
        healRangerButton.setFocusPainted(false);//Remove border around text in button

        //button 3
        healMageButton = new JButton("Mage");
        healMageButton.setSize(200, 70);
        healMageButton.setLocation(1050, 630);
        healMageButton.setFont(pixelMplus.deriveFont(30f));
        healMageButton.setBackground(Color.white);
        healMageButton.setBorder(null); //Remove border around button
        healMageButton.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        healHealerButton = new JButton("Healer");
        healHealerButton.setSize(200, 70);
        healHealerButton.setLocation(840, 630);
        healHealerButton.setFont(pixelMplus.deriveFont(30f));
        healHealerButton.setBackground(Color.white);
        healHealerButton.setBorder(null); //Remove border around button
        healHealerButton.setFocusPainted(false);//Remove border around text in button

    }

    /**
     *
     */
    private void getInventory() {

        //Minor Health Potion
        potion1 = new JButton(potions.minorHealthGif);
        potion1.setBounds(200, 60, 41, 62);
        potion1.setBackground(Color.white);
        potion1.setBorder(null);
        potion1.setFocusPainted(false);

        //Lesser Health Potion
        potion2 = new JButton(potions.lesserHealthGif);
        potion2.setBounds(250, 60, 46, 62);
        potion2.setBackground(Color.white);
        potion2.setBorder(null);
        potion2.setFocusPainted(false);

        //Major Health Potion
        potion3 = new JButton(potions.majorHealthGif);
        potion3.setBounds(310, 60, 55, 64);
        potion3.setBackground(Color.white);
        potion3.setBorder(null);
        potion3.setFocusPainted(false);

        //Minor Block Potion
        potion4 = new JButton(potions.minorBlockGif);
        potion4.setBounds(450, 60, 42, 63);
        potion4.setBackground(Color.white);
        potion4.setBorder(null);
        potion4.setFocusPainted(false);

        //Lesser Block Potion
        potion5 = new JButton(potions.lesserBlockGif);
        potion5.setBounds(500, 60, 47, 63);
        potion5.setBackground(Color.white);
        potion5.setBorder(null);
        potion5.setFocusPainted(false);

        //Major Block Potion
        potion6 = new JButton(potions.majorBlockGif);
        potion6.setBounds(560, 60, 59, 64);
        potion6.setBackground(Color.white);
        potion6.setBorder(null);
        potion6.setFocusPainted(false);

        //Minor Energy Potion
        potion7 = new JButton(potions.minorEnergyGif);
        potion7.setBounds(700, 60, 40, 63);
        potion7.setBackground(Color.white);
        potion7.setBorder(null);
        potion7.setFocusPainted(false);

        //Lesser Energy Potion
        potion8 = new JButton(potions.lesserEnergyGif);
        potion8.setBounds(750, 60, 46, 63);
        potion8.setBackground(Color.white);
        potion8.setBorder(null);
        potion8.setFocusPainted(false);

        //Major Energy Potion
        potion9 = new JButton(potions.majorEnergyGif);
        potion9.setBounds(810, 60, 59, 64);
        potion9.setBackground(Color.white);
        potion9.setBorder(null);
        potion9.setFocusPainted(false);

        //Minor Strength Potion
        potion10 = new JButton(potions.minorStrGif);
        potion10.setBounds(950, 60, 38, 63);
        potion10.setBackground(Color.white);
        potion10.setBorder(null);
        potion10.setFocusPainted(false);

        //Lesser Energy Potion
        potion11 = new JButton(potions.lesserStrGif);
        potion11.setBounds(1000, 60, 42, 63);
        potion11.setBackground(Color.white);
        potion11.setBorder(null);
        potion11.setFocusPainted(false);

        //Major Energy Potion
        potion12 = new JButton(potions.majorStrGif);
        potion12.setBounds(1050, 60, 53, 64);
        potion12.setBackground(Color.white);
        potion12.setBorder(null);
        potion12.setFocusPainted(false);

        //Labels
        inventoryHealth = new JLabel("Health");
        inventoryHealth.setFont(pixelMplus.deriveFont(30f));
        inventoryHealth.setForeground(Color.black);
        Dimension inventoryHealthSize = inventoryHealth.getPreferredSize();
        inventoryHealth.setBounds(240, 25, inventoryHealthSize.width, inventoryHealthSize.height);

        inventoryStr = new JLabel("Strength");
        inventoryStr.setFont(pixelMplus.deriveFont(30f));
        inventoryStr.setForeground(Color.black);
        Dimension inventoryStrSize = inventoryStr.getPreferredSize();
        inventoryStr.setBounds(965, 25, inventoryStrSize.width, inventoryStrSize.height);

        inventoryEnergy = new JLabel("Energy");
        inventoryEnergy.setFont(pixelMplus.deriveFont(30f));
        inventoryEnergy.setForeground(Color.black);
        Dimension inventoryEnergySize = inventoryEnergy.getPreferredSize();
        inventoryEnergy.setBounds(740, 25, inventoryEnergySize.width, inventoryEnergySize.height);

        inventoryBlock = new JLabel("Block");
        inventoryBlock.setFont(pixelMplus.deriveFont(30f));
        inventoryBlock.setForeground(Color.black);
        Dimension inventoryBlockSize = inventoryBlock.getPreferredSize();
        inventoryBlock.setBounds(495, 25, inventoryBlockSize.width, inventoryBlockSize.height);

        //Potion owned
        potion1Label.setFont(pixelMplus.deriveFont(30f));
        potion1Label.setForeground(Color.black);
        Dimension potion1LabelSize = potion1Label.getPreferredSize();
        potion1Label.setBounds(210, 125, potion1LabelSize.width, potion1LabelSize.height);

        potion2Label.setFont(pixelMplus.deriveFont(30f));
        potion2Label.setForeground(Color.black);
        Dimension potion2LabelSize = potion2Label.getPreferredSize();
        potion2Label.setBounds(265, 125, potion2LabelSize.width, potion2LabelSize.height);

        potion3Label.setFont(pixelMplus.deriveFont(30f));
        potion3Label.setForeground(Color.black);
        Dimension potion3LabelSize = potion3Label.getPreferredSize();
        potion3Label.setBounds(330, 125, potion3LabelSize.width, potion3LabelSize.height);

        potion4Label.setFont(pixelMplus.deriveFont(30f));
        potion4Label.setForeground(Color.black);
        Dimension potion4LabelSize = potion4Label.getPreferredSize();
        potion4Label.setBounds(470, 125, potion4LabelSize.width, potion4LabelSize.height);

        potion5Label.setFont(pixelMplus.deriveFont(30f));
        potion5Label.setForeground(Color.black);
        Dimension potion5LabelSize = potion5Label.getPreferredSize();
        potion5Label.setBounds(520, 125, potion5LabelSize.width, potion5LabelSize.height);

        potion6Label.setFont(pixelMplus.deriveFont(30f));
        potion6Label.setForeground(Color.black);
        Dimension potion6LabelSize = potion6Label.getPreferredSize();
        potion6Label.setBounds(580, 125, potion6LabelSize.width, potion6LabelSize.height);

        potion7Label.setFont(pixelMplus.deriveFont(30f));
        potion7Label.setForeground(Color.black);
        Dimension potion7LabelSize = potion7Label.getPreferredSize();
        potion7Label.setBounds(707, 125, potion7LabelSize.width, potion7LabelSize.height);

        potion8Label.setFont(pixelMplus.deriveFont(30f));
        potion8Label.setForeground(Color.black);
        Dimension potion8LabelSize = potion8Label.getPreferredSize();
        potion8Label.setBounds(767, 125, potion8LabelSize.width, potion8LabelSize.height);

        potion9Label.setFont(pixelMplus.deriveFont(30f));
        potion9Label.setForeground(Color.black);
        Dimension potion9LabelSize = potion9Label.getPreferredSize();
        potion9Label.setBounds(830, 125, potion9LabelSize.width, potion9LabelSize.height);

        potion10Label.setFont(pixelMplus.deriveFont(30f));
        potion10Label.setForeground(Color.black);
        Dimension potion10LabelSize = potion10Label.getPreferredSize();
        potion10Label.setBounds(967, 125, potion10LabelSize.width, potion10LabelSize.height);

        potion11Label.setFont(pixelMplus.deriveFont(30f));
        potion11Label.setForeground(Color.black);
        Dimension potion11LabelSize = potion11Label.getPreferredSize();
        potion11Label.setBounds(1013, 125, potion11LabelSize.width, potion11LabelSize.height);

        potion12Label.setFont(pixelMplus.deriveFont(30f));
        potion12Label.setForeground(Color.black);
        Dimension potion12LabelSize = potion12Label.getPreferredSize();
        potion12Label.setBounds(1070, 125, potion12LabelSize.width, potion12LabelSize.height);
    }

    /**
     *
     */
    //Set al "stats" for labels.
    private void importLabels(){
        playerStr.setFont(pixelMplus.deriveFont(30f));
        playerStr.setForeground(Color.black);
        Dimension playersHpSize = playerStr.getPreferredSize();
        playerStr.setBounds(30, 600, playersHpSize.width+50, playersHpSize.height);

        wolf1Hp.setFont(pixelMplus.deriveFont(30f));
        wolf1Hp.setForeground(Color.black);
        Dimension wolf1HpSize = wolf1Hp.getPreferredSize();
        wolf1Hp.setBounds(680, 560, wolf1HpSize.width+50, wolf1HpSize.height);

        wolf2Hp.setFont(pixelMplus.deriveFont(30f));
        wolf2Hp.setForeground(Color.black);
        Dimension wolf2HpSize = wolf2Hp.getPreferredSize();
        wolf2Hp.setBounds(680, 595, wolf2HpSize.width+50, wolf2HpSize.height);

        wolf3Hp.setFont(pixelMplus.deriveFont(30f));
        wolf3Hp.setForeground(Color.black);
        Dimension wolf3HpSize = wolf3Hp.getPreferredSize();
        wolf3Hp.setBounds(680, 630, wolf3HpSize.width+50, wolf3HpSize.height);

        wolf4Hp.setFont(pixelMplus.deriveFont(30f));
        wolf4Hp.setForeground(Color.black);
        Dimension wolf4HpSize = wolf4Hp.getPreferredSize();
        wolf4Hp.setBounds(680, 665, wolf4HpSize.width+50, wolf4HpSize.height);

        player1Hp.setFont(pixelMplus.deriveFont(30f));
        player1Hp.setForeground(Color.black);
        Dimension player1HpSize = player1Hp.getPreferredSize();
        player1Hp.setBounds(370, 560, player1HpSize.width+50, player1HpSize.height);

        player2Hp.setFont(pixelMplus.deriveFont(30f));
        player2Hp.setForeground(Color.black);
        Dimension player2HpSize = player1Hp.getPreferredSize();
        player2Hp.setBounds(370, 595, player2HpSize.width+50, player2HpSize.height);

        player3Hp.setFont(pixelMplus.deriveFont(30f));
        player3Hp.setForeground(Color.black);
        Dimension player3HpSize = player3Hp.getPreferredSize();
        player3Hp.setBounds(370, 630, player3HpSize.width+50, player3HpSize.height);

        player4Hp.setFont(pixelMplus.deriveFont(30f));
        player4Hp.setForeground(Color.black);
        Dimension player4HpSize = player4Hp.getPreferredSize();
        player4Hp.setBounds(370, 665, player4HpSize.width+50, player4HpSize.height);

        energy = new JLabel("Energy: 5");
        energy.setFont(pixelMplus.deriveFont(30f));
        energy.setForeground(Color.black);
        Dimension energySize = energy.getPreferredSize();
        energy.setBounds(30, 638, energySize.width+50, energySize.height);

        block.setFont(pixelMplus.deriveFont(30f));
        block.setForeground(Color.black);
        Dimension blockSize = energy.getPreferredSize();
        block.setBounds(30, 670, blockSize.width+200, blockSize.height);

        whosTurn = new JLabel("Warrior's turn");
        whosTurn.setFont(pixelMplus.deriveFont(30f));
        whosTurn.setForeground(Color.black);
        whosTurn.setBackground(Color.blue);
        Dimension whoSize = whosTurn.getPreferredSize();
        whosTurn.setBounds(30, 560, whoSize.width+50, whoSize.height);
    }

    /**
     *
     */
    //Set all stats for buttons.
    private void importButtons(){
        //Attack button
        attackButton = new JButton("Attack");
        attackButton.setSize(200, 70);
        attackButton.setLocation(840, 555);
        attackButton.setFont(pixelMplus.deriveFont(30f));
        attackButton.setBackground(Color.white);
        attackButton.setBorder(null); //Remove border around button
        attackButton.setFocusPainted(false);//Remove border around text in button

        //Block button
        blockButton = new JButton("Block");
        blockButton.setSize(200, 70);
        blockButton.setLocation(1050, 555);
        blockButton.setFont(pixelMplus.deriveFont(30f));
        blockButton.setBackground(Color.white);
        blockButton.setBorder(null); //Remove border around button
        blockButton.setFocusPainted(false);//Remove border around text in button

        //Item Button
        itemButton = new JButton("Item");
        itemButton.setSize(200, 70);
        itemButton.setLocation(1050, 630);
        itemButton.setFont(pixelMplus.deriveFont(30f));
        itemButton.setBackground(Color.white);
        itemButton.setBorder(null); //Remove border around button
        itemButton.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        skillButton = new JButton("Skill");
        skillButton.setSize(200, 70);
        skillButton.setLocation(840, 630);
        skillButton.setFont(pixelMplus.deriveFont(30f));
        skillButton.setBackground(Color.white);
        skillButton.setBorder(null); //Remove border around button
        skillButton.setFocusPainted(false);//Remove border around text in button

        //End Turn Button
        endTurnButton = new JButton("End turn");
        endTurnButton.setSize(200, 70);
        endTurnButton.setLocation(540, 468);
        endTurnButton.setFont(pixelMplus.deriveFont(30f));
        endTurnButton.setBackground(Color.white);
        endTurnButton.setBorder(null); //Remove border around button
        endTurnButton.setFocusPainted(false);//Remove border around text in button
    }

    /**
     *
     */
    //Get all OldClasses.party-gif's.
    private void importPartyGif(){
        warrior = new JLabel();
        warrior.setIcon(new ImageIcon("warrior.gif"));

        healer = new JLabel();
        healer.setIcon(new ImageIcon("healer.gif"));

        ranger = new JLabel();
        ranger.setIcon(new ImageIcon("ranger.gif"));

        mage = new JLabel();
        mage.setIcon(new ImageIcon("mage.gif"));

        Dimension warriorSize = warrior.getPreferredSize();
        Dimension healerSize = healer.getPreferredSize();
        Dimension rangerSize = ranger.getPreferredSize();
        Dimension mageSize = mage.getPreferredSize();

        warrior.setBounds(170, 210, warriorSize.width+50, warriorSize.height);
        healer.setBounds(-30, 210, healerSize.width+50, healerSize.height);
        ranger.setBounds(70, 290, rangerSize.width+50, rangerSize.height);
        mage.setBounds(-110, 290, mageSize.width+50, mageSize.height);
    }

    /**
     *
     */
    //Get wolf gif.
    private void importWolfGif(){
        wolf1 = new JLabel();
        wolf1.setIcon(new ImageIcon("forestMob.gif"));
        Dimension wolfSize = wolf1.getPreferredSize();
        wolf1.setBounds(850, 320, wolfSize.width+50, wolfSize.height);

        wolf2 = new JLabel();
        wolf2.setIcon(new ImageIcon("forestMob.gif"));
        wolf2.setBounds(1030, 320, wolfSize.width+50, wolfSize.height);

        wolf3 = new JLabel();
        wolf3.setIcon(new ImageIcon("forestMob.gif"));
        wolf3.setBounds(900, 400, wolfSize.width+50, wolfSize.height);

        wolf4 = new JLabel();
        wolf4.setIcon(new ImageIcon("forestMob.gif"));
        wolf4.setBounds(1080, 400, wolfSize.width+50, wolfSize.height);
    }


    /**
     *
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}